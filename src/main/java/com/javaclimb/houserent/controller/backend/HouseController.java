package com.javaclimb.houserent.controller.backend;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.houserent.common.base.BaseController;
import com.javaclimb.houserent.common.constant.Constant;
import com.javaclimb.houserent.common.dto.JsonResult;
import com.javaclimb.houserent.common.enums.HouseStatusEnum;
import com.javaclimb.houserent.common.util.PageUtil;
import com.javaclimb.houserent.entity.House;
import com.javaclimb.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;


//房子控制器
@Controller
@RequestMapping("/admin/house")
public class HouseController extends BaseController {
    @Autowired
    private HouseService houseService;
    //进入房子管理界面
    @RequestMapping("" )
    public String houseList(@RequestParam(value = "page",defaultValue = "1")Long pageNumber, @RequestParam(value = "size",defaultValue = "6")Long pageSize, Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        House condition = new House();
        //如果用户是管理员，则可以查询所有房子，否则仅能查询自己的房子
        if(!loginUserIsAdmin()){
            condition.setUserId(getLoginUserId());
        }
        Page<House> housePage = houseService.findAll(page,condition);
        model.addAttribute("pageInfo",housePage);
        model.addAttribute("pagePrefix","/admin/house?");
        model.addAttribute("isAdmin",loginUserIsAdmin());
        return "admin/house-list";
    }
    //进入房子发布页面
    @RequestMapping("/publish")
    public String publish(@RequestParam(value = "id",required = false)Long id,Model model){
        House house =new House();
        //编辑页面
        if(id!=null){
            house = houseService.get(id);
            if(house==null){
                return renderNotFound();
            }
            //在无权限的情况下编辑他人的房子 跳转403
            if(!loginUserIsAdmin() && !Objects.equals(house.getUserId(),getLoginUserId())){
                return renderNotAllowAccess();
            }
        }
        model.addAttribute("house",house);
        return "admin/house-publish";
    }
    //发布房子提交
    @RequestMapping("/publish/submit")
    @ResponseBody
    public JsonResult publishSubmit(House house, @RequestParam("key")String key, HttpSession session){
        try{
            if(house.getId() == null){
                house.setCreateTime(new Date());
                house.setUserId(getLoginUserId());
            }else {
                //发布不存在的id的房子
                House queryHouse=houseService.get(house.getId());
                if(queryHouse==null){
                    return JsonResult.error("发布失败，没有此房屋");
                }
                //在无权限的情况下编辑他人的房子
                if(!loginUserIsAdmin() && !Objects.equals(house.getUserId(),getLoginUserId())){
                    return JsonResult.error("发布失败，无权限编辑");
                }
            }
            house.setStatus(HouseStatusEnum.NOT_CHECK.getValue());
            //获取轮播图
            String sessionKey = Constant.SESSION_IMG_PREFIX + key;
            List<String> imgList = (List<String>) session.getAttribute(sessionKey);
            if(imgList!=null&&imgList.size()>0){
                //把轮播图转换成Json格式储存
                house.setSlideUrl(JSON.toJSONString(imgList));
                //把轮播图的第一个图放到缩略图
                house.setThumbnailUrl(imgList.get(0));
            }
            houseService.insertOrUpdate(house);
        }catch (Exception e){
            return JsonResult.error("发布失败，请填写完整信息");
        }
        return JsonResult.success("发布成功",house.getId());
    }

    //上架房子
    @RequestMapping("/up")
    @ResponseBody
    public JsonResult upHouse(@RequestParam("id")Long id){
        try{
            House house = houseService.get(id);
            if(house==null){
                return JsonResult.error("没有此房屋");
            }
            //在无权限的情况下下架他人的房子
            if(!loginUserIsAdmin() && !Objects.equals(house.getUserId(),getLoginUserId())){
                return JsonResult.error("无权限操作");
            }
            if(Objects.equals(house.getStatus(),HouseStatusEnum.HAS_RENT.getValue())){
                return JsonResult.error("房子租出中");
            }
            house.setStatus(HouseStatusEnum.NOT_RENT.getValue());
            houseService.update(house);
        }catch (Exception e){
            return JsonResult.error("上架失败");
        }
        return JsonResult.success("上架成功");
    }

    //下架房子
    @RequestMapping("/down")
    @ResponseBody
    public JsonResult downHouse(@RequestParam("id")Long id){
        try{
            House house = houseService.get(id);
                if(house==null){
                    return JsonResult.error("没有此房屋");
                }
                //在无权限的情况下下架他人的房子
                if(!loginUserIsAdmin() && !Objects.equals(house.getUserId(),getLoginUserId())){
                    return JsonResult.error("无权限操作");
                }
                if(Objects.equals(house.getStatus(),HouseStatusEnum.HAS_RENT.getValue())){
                    return JsonResult.error("房子租出中");
                }
                house.setStatus(HouseStatusEnum.HAS_DOWN.getValue());
                houseService.update(house);
        }catch (Exception e){
            return JsonResult.error("下架失败");
        }
        return JsonResult.success("下架成功");
    }

    //审核房子通过
    @RequestMapping("/checkPass")
    @ResponseBody
    public JsonResult checkPassHouse(@RequestParam("id")Long id){
        try{
            House house = houseService.get(id);
            if(house==null){
                return JsonResult.error("没有此房屋");
            }
            //仅限管理员审核
            if(!loginUserIsAdmin()){
                return JsonResult.error("无权限操作");
            }
            if(!Objects.equals(house.getStatus(),HouseStatusEnum.NOT_CHECK.getValue())){
                return JsonResult.error("仅能审核待定房子");
            }
            house.setStatus(HouseStatusEnum.NOT_RENT.getValue());
            houseService.update(house);
        }catch (Exception e){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功");
    }

    //审核房子不通过
    @RequestMapping("/checkReject")
    @ResponseBody
    public JsonResult checkRejectHouse(@RequestParam("id")Long id){
        try{
            House house = houseService.get(id);
            if(house==null){
                return JsonResult.error("没有此房屋");
            }
            //仅限管理员审核
            if(!loginUserIsAdmin()){
                return JsonResult.error("无权限操作");
            }
            if(!Objects.equals(house.getStatus(),HouseStatusEnum.NOT_CHECK.getValue())){
                return JsonResult.error("仅能审核待定房子");
            }
            house.setStatus(HouseStatusEnum.CHECK_REJECT.getValue());
            houseService.update(house);
        }catch (Exception e){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功");
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult deleteHouse(@RequestParam("id")Long id){
        try{
            House house = houseService.get(id);
            if(house==null){
                return JsonResult.error("没有此房屋");
            }
            //仅限管理员删除
            if(!loginUserIsAdmin() && !Objects.equals(house.getUserId(),getLoginUserId())){
                return JsonResult.error("无权限操作");
            }
            if(Objects.equals(house.getStatus(),HouseStatusEnum.HAS_RENT.getValue())){
                return JsonResult.error("出租状态无法删除");
            }
            houseService.delete(id);
        }catch (Exception e){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功");
    }
}

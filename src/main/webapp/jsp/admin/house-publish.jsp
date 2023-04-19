<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp" %>
<link rel="stylesheet" href="/assets/plugins/bootstrap-fileinput/css/fileinput.min.css">
<script src="/assets/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/assets/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<section class="p-0">
    <div class="container-fluid p-0">
        <div class="row">
            <%@ include file="../common/admin-left.jsp" %>
            <div class="col-lg-9 col-md-8 col-sm-12">
                <section style="padding-top: 10px;">
                    <div class="container">
                        <div class="row">
                            <form id="houseForm">
                                <input type="hidden" id="key" name="key">
                                <div class="col-lg-12 col-md-12">
                                    <div class="submit-page form-simple">
                                        <div class="frm_submit_block">
                                            <h3>基本信息</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>出租类型</label>
                                                    <select name="rentType" class="form-control">
                                                        <option value="whole">整租</option>
                                                        <option value="share">合租</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>月租金<a href="#" class="tip-topdata" data-tip="日租金=月租金/30"><i
                                                            class="ti-help"></i></a></label>
                                                    <input type="number" name="monthRent" class="form-control">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>所属城市</label>
                                                    <select name="city" class="form-control">
                                                        <option value="北京">北京</option>
                                                        <option value="上海">上海</option>
                                                        <option value="广州">广州</option>
                                                        <option value="深圳">深圳</option>
                                                        <option value="武汉">武汉</option>
                                                        <option value="重庆">重庆</option>
                                                        <option value="杭州">杭州</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>房子标题信息<a href="#" class="tip-topdata" data-tip="合租-四合院"><i
                                                            class="ti-help"></i></a></label>
                                                    <input type="text" name="title" class="form-control">
                                                </div>
                                                <div class="form-group col-md-8">
                                                    <label>房子详细地址信息<a href="#" class="tip-topdata" data-tip="北京四合院"><i
                                                            class="ti-help"></i></a></label>
                                                    <input type="text" name="address" class="form-control">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label>经纬度坐标<a
                                                            href="https://api.map.baidu.com/lbsapi/getpoint/index.html"
                                                            target="_blank">点击获取</a>
                                                        <a href="#" class="tip-topdata" data-tip="经纬度"><i
                                                                class="ti-help"></i></a></label>
                                                    <input type="text" name="longitudeLatitude" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="frm_submit_block">
                                            <h3>结构信息</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>房产证号</label>
                                                    <input type="text" name="cetificateNo" class="form-control">
                                                    </input>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>面积<a href="#" class="tip-topdata"
                                                                data-tip="整租类型时为完整面积，合租时为单间面积"><i
                                                            class="ti-help"></i></a></label>
                                                    <input type="number" name="monthRent" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>朝向</label>
                                                    <input type="text" name="direction" class="form-control">
                                                    </input>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>是否有空调</label>
                                                    <select name="hasAirConditioner" class="form-control">
                                                        <option value="1">有空调</option>
                                                        <option value="0">无空调</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>卧室数量</label>
                                                    <select name="bedroomNum" class="form-control">
                                                        <option value="1">1个</option>
                                                        <option value="2">2个</option>
                                                        <option value="3">3个</option>
                                                        <option value="4">4个</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>客厅数量</label>
                                                    <select name="livingRoomNum" class="form-control">
                                                        <option value="1">1个</option>
                                                        <option value="2">2个</option>
                                                        <option value="3">3个</option>
                                                        <option value="4">4个</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>厨房数量</label>
                                                    <select name="kitchenNum" class="form-control">
                                                        <option value="1">1个</option>
                                                        <option value="2">2个</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>厕所数量</label>
                                                    <select name="toiletNum" class="form-control">
                                                        <option value="1">1个</option>
                                                        <option value="2">2个</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="frm_submit_block">
                                            <h3>建筑信息</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>建成年份</label>
                                                    <input type="number" name="buildYear" class="form-control">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>所在楼层</label>
                                                    <input type="number" name="floor" class="form-control">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>总楼层</label>
                                                    <input type="number" name="maxFloor" class="form-control">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>是否有电梯</label>
                                                    <select name="hasElevator" class="form-control">
                                                        <option value="1">有电梯</option>
                                                        <option value="0">无电梯</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="frm_submit_block">
                                            <h3>联系人信息</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label>联系人姓名</label>
                                                    <input type="text" name="contactName" class="form-control">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label>联系人电话</label>
                                                    <input type="text" name="contactPhone" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="frm_submit_block">
                                            <h3>详细描述</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-12">
                                                    <label>房屋详细描述</label>
                                                    <textarea class="form-control ht-120" name="content"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="frm_submit_block">
                                            <h3>轮播图上传</h3>
                                            <div class="form-row">
                                                <div class="form-group col-md-12">
                                                    <input type="file" name="file" id="file" multiple class="file-loading"/>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-12 col-md-12">
                                                <button class="btn btn-theme bg-2" type="button"
                                                        onclick="submitHouse()">发布
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</section>

<%@ include file="../common/footer.jsp" %>
<script>
    $(function (){
        var timestamp = Date.parse(new Date());
        $("#key").val(timestamp);
         $("#file").fileinput({
             language:'zh',     //设置语言
             uploadUrl:"/file/upload?key="+timestamp,   //上传地址
             allowedFileExtensions:['png','jpg','jpeg','gif'] , //允许上传的文件后缀
             showUpload:true, // 显示批量上传的按钮
             showCaption:false, //不显示标题
         })

    })
</script>
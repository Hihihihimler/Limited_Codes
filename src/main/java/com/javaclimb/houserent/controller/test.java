//import org.springframework.stereotype.Service;
//
//@RestController
//@RequestMapping("/api/collection")
//public class CollectionController {
//    @Autowired
//    private CollectionService collectionService;
//
//    // 将房源添加到租客的收藏夹中
//    @PostMapping("/add")
//    public ResponseEntity<?> addHouseToCollection(@RequestBody Map<String, Long> requestBody) {
//        Long tenantId = requestBody.get("tenantId");
//        Long houseId = requestBody.get("houseId");
//        collectionService.addHouseToCollection(tenantId, houseId);
//        return ResponseEntity.ok("房源已成功添加到收藏夹中");
//    }
//
//    // 将房源从租客的收藏夹中删除
//    @PostMapping("/remove")
//    public ResponseEntity<?> removeHouseFromCollection(@RequestBody Map<String, Long> requestBody) {
//        Long tenantId = requestBody.get("tenantId");
//        Long houseId = requestBody.get("houseId");
//        collectionService.removeHouseFromCollection(tenantId, houseId);
//        return ResponseEntity.ok("房源已成功从收藏夹中删除");
//    }
//
//    // 获取租客收藏的所有房源信息
//    @RestController
//    @RequestMapping("/house")
//    public class HouseController {
//        @Autowired
//        private HouseService houseService;
//        @PostMapping("/update")
//        public Result updateHouse(@RequestBody House house) {
//            if (StringUtils.isEmpty(house.getTitle()) || house.getPrice() <= 0) {
//                return ResultUtil.error("房屋信息不合法");
//            }
//            boolean success = houseService.updateHouse(house);
//            if (success) {
//                return ResultUtil.success();
//            } else {
//                return ResultUtil.error("更新房屋信息失败");
//            }
//        }
//    }
//
//    @RestController
//    @RequestMapping("/orders")
//    public class OrderController {
//        @Autowired
//        private OrderService orderService;
//        @GetMapping("/")
//        public List<Order> getAllOrders() {
//            return orderService.getAllOrders();
//        }
//        @GetMapping("/{orderId}")
//        public Order getOrder(@PathVariable("orderId") Long orderId) {
//            return orderService.getOrderById(orderId);
//        }
//        @PostMapping("/")
//        public Order createOrder(@RequestBody Order order) {
//            return orderService.createOrder(order);
//        }
//        @PutMapping("/{orderId}")
//        public Order updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
//            return orderService.updateOrder(orderId, order);
//        }
//        @DeleteMapping("/{orderId}")
//        public void deleteOrder(@PathVariable("orderId") Long orderId) {
//            orderService.deleteOrder(orderId);
//        }
//    }
//
//    @RestController
//    @RequestMapping("/push")
//    public class PushController {
//
//        @Autowired
//        private HouseService houseService;
//
//        @Autowired
//        private UserService userService;
//
//        @PostMapping("/toUser")
//        public String pushHouseInfoToUser(@RequestParam("userId") Long userId) {
//            //获取用户信息
//            User user = userService.getUserById(userId);
//            //获取可用的房屋信息
//            List<House> houses = houseService.getAvailableHouses();
//            if (houses.isEmpty()) {
//                return "暂无房屋信息可供推送";
//            }
//            //发送推送通知
//            StringBuilder sb = new StringBuilder();
//            sb.append("尊敬的").append(user.getUsername()).append("，以下房屋信息符合您的需求：");
//            for (House house : houses) {
//                sb.append("房屋编号：").append(house.getId())
//                        .append("，租金：").append(house.getPrice())
//                        .append("，地址：").append(house.getAddress())
//                        .append("\n");
//            }
//            String pushMsg = sb.toString();
//            //调用推送接口
//            PushService.pushToUser(user.getDeviceToken(), pushMsg);
//            return "推送成功";
//        }
//    }
//
//
//    @RestController
//    @RequestMapping("/feedback")
//    public class FeedbackController {
//
//        @Autowired
//        private FeedbackService feedbackService;
//
//        // 提交反馈
//        @PostMapping("")
//        public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
//            feedbackService.submitFeedback(feedback);
//            return ResponseEntity.ok().build();
//        }
//
//        // 查看反馈
//        @GetMapping("")
//        public ResponseEntity<List<Feedback>> viewFeedback() {
//            List<Feedback> feedbackList = feedbackService.viewFeedback();
//            return ResponseEntity.ok(feedbackList);
//        }
//
//        // 删除反馈
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
//            feedbackService.deleteFeedback(id);
//            return ResponseEntity.ok().build();
//        }
//
//    }

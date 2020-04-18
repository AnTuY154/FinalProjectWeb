<%-- 
    Document   : futureOrder
    Created on : Oct 13, 2019, 8:43:19 PM
    Author     : Darkness_King
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="ulti.HtmlHelper"%>
<%@page import="entity.OrderLine"%>
<%@page import="entity.orderTemp"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>JSP Page</title>
        <%
            ArrayList<orderTemp> orderTemp = (ArrayList<orderTemp>) request.getAttribute("xtempsize");
            Integer pageindex = (Integer) request.getAttribute("pageindex");
            Integer pagecount = (Integer) request.getAttribute("pagecount");
            Integer size = (Integer) session.getAttribute("tempsize");
             DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");
        %>
    </head>
    <body>
        <div>


            <div class="container-fluid text-left">    
                <nav class="navbar navbar-expand-md navbar-dark bg-dark">
                    <div class="container">
                        <form action="getProduct">
                            <button type="submit" class="btn btn-primary">Trang chủ</button>  
                        </form>
                        <form action="getOrderTemp">
                            <button type="submit" class="btn btn-primary">Đơn đặt trước(<%=size%>)</button>                    
                        </form>
                    </div>
                </nav>
            </div>
            <%if (orderTemp != null) {%>
            <div class="table-responsive">
                <table class="table">
                    <%int a = -1;%>
                    <%for (orderTemp or : orderTemp) {%>
                    <%float total=0;%>
                    <tr>
                        <td>
                            <h2><%=or.getCustomer().getName()%>-<%=or.getCustomer().getAddress()%></h2>
                          
                        </td>
                    </tr>
                    <tr>


                        <td><h6>Product Name</h6></td>
                        <td><h6>Price</h6></td>
                        <td><h6>Quantity</h6></td>
                        <td><h6>Total</h6></td>
                        <td><h6>Date</h6></td>
                        <td><h6>Note</h6></td>

                    </tr>
                    <%for (OrderLine orderline : or.getOrderLine()) {%>
                    <tr>
                        <td>
                            <%=orderline.getProduct().getName()%>
                        </td>
                        <td>
                            <%=orderline.getPrice()%>
                        </td>
                        <td>
                            <%=orderline.getQuantity()%>
                        </td>
                        <td>
                            <%=orderline.getTotal()%>
                            <%total=total+orderline.getTotal();%>
                        </td>
                        <td>
                            <%=orderline.getOrder().getDate()%>
                        </td>
                        <td>
                            <%=orderline.getOrder().getNote()%>
                        </td>


                        <%if (a != orderline.getOrder().getId()) {%>
                        <%a = orderline.getOrder().getId();%>
                        <td > 
                            <form id="<%=orderline.getOrder().getId()%>" action="updateOrderTemp" method="POST">
                                <button   class="btn btn-outline-dark" type="submit">Hoàn thành đơn hàng</button>
                                <input type="hidden" name ="orderID" value="<%=orderline.getOrder().getId()%>">
                                <input type="hidden" name ="mess" value="1">
                            </form>
                        </td>
                        <td>
                            <form id="<%=orderline.getOrder().getId()%>"  action="updateOrderTemp" method="POST">
                                <button  class="btn btn-outline-dark" type="submit"> Hủy đơn hàng </button>
                                <input type="hidden" name ="orderID" value="<%=orderline.getOrder().getId()%>">
                                <input type="hidden" name ="mess"value="0">
                            </form>
                        </td>  
                        <%}%>
                    </tr>
                    <%}%>
                    <tr>
                        <td>
                            <h2>Tổng giá trị đơn hàng: &nbsp<%=decimalFormat.format(total)%></h2>
                        </td>
                    </tr>
                  
                    <%}%>
                </table>
                <div>    <%=HtmlHelper.pager(pageindex, pagecount, 2)%></div>
            </div>
            <%}%>
        </div>
    </body>
    <script type="text/javascript">
//        function checkDelete(form_id) {
//            var result = confirm("Bạn có chắc chắc muốn hủy đơn hàng không?");
//            if (result) {
//                document.getElementById(form_id).submit();
//                alert('Xóa dữ liệu thành công');
//            } else {
//
//            }
//        }
//        
//         function checkDone(form_id) {
//            var result = confirm("Bạn có chắc chắc đã hoàn thành đơn hàng ?");
//            if (result) {
//                document.getElementById(form_id).submit();
//                alert('Đơn đã hoàn thành');
//            } else {
//
//            }
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>

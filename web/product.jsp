

<%-- 
    Document   : product
    Created on : Oct 9, 2019, 11:27:22 PM
    Author     : Darkness_King
--%>

<%@page import="entity.Customer"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entity.OrderLine"%>
<%@page import="entity.Order"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <title>JSP Page</title>
        <%
            ArrayList<Product> a = (ArrayList<Product>) session.getAttribute("listPro");
            //  ArrayList<Customer> cus = (ArrayList<Customer>) request.getAttribute("listCus");
            Order order = (Order) session.getAttribute("cart");
            DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");
            String xtotal = null;
            Integer size = (Integer) session.getAttribute("tempsize");
        %>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <form action="getProduct">
                    <button type="submit" class="btn btn-primary">Trang chủ</button>  
                </form>
                <form action="getOrderTemp" id="temp">
                    <button type="submit" class="btn btn-primary">Đơn đặt trước(<%=size%>)</button>                    
                </form>
                <form action="statistical">
                    <button type="submit" class="btn btn-primary">Kiểm tra doanh thu</button>
                </form>
                 <form action="insertProduct">
                    <button type="submit" class="btn btn-primary">Thêm sản phẩm mới</button>
                </form>
                  <form action="updateProduct">
                    <button type="submit" class="btn btn-primary">Chỉnh sửa sản phẩm</button>
                </form>
                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <div class="input-group input-group-sm">
                        <nav class="nav">
                            <form action="getoneProduct" method="GET" id="deleteForm">
                                <select  style="width: 300px;height: 50px;margin: 10px;" name="product" onchange="this.form.submit()">
                                    <option>Chọn sản phẩm</option>
                                    <%for (Product p : a) {%>
                                    <option value="<%=p.getId()%>"><%=p.getName()%>(<%=p.getId()%>)</option>
                                    <%}%> 
                                </select>
                                <% if (order == null) { %>
                                <%} else {%>
                                <%  xtotal = decimalFormat.format(order.getTotal());%>
                                <%for (OrderLine or : order.getLines()) {%>
                                <input type="hidden" name="<%=or.getProduct().getId()%>" id="<%=or.getLine()%>u" value="<%=or.getQuantity()%>" required>
                                <input type="hidden" name="<%=or.getProduct().getId()%>currentPrice" id="<%=or.getLine()%>p" value="<%=or.getPrice()%>"required>
                                <%}%>
                                <%}%>
                                <input type="hidden" name="prodelete" id="delete" value="0">
                                <input type="hidden" name="xsubmit" id="zsubmit" value="0">
                            </form>
                        </nav>
                    </div>
                    <a class="btn btn-success btn-sm ml-3" href="#">
                        <%if (order != null) {%>
                        <p class="fa fa-shopping-cart"></p> Cart
                        <span class="badge badge-light"><%=order.getLines().size()%></span>
                        <%} else {%>
                        <p class="fa fa-shopping-cart"></p> Cart
                        <span class="badge badge-light">0</span>
                        <%}%>
                    </a>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Shopping</h1>
            </div>
        </section>
        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (order == null) { %>
                                <%} else {%>
                                <tr>
                                    <% for (OrderLine l : order.getLines()) {%>
                                    <td><%=l.getProduct().getName()%></td>
                                    <%
                                      
                                        String xformat = decimalFormat.format(l.getPrice());
                                        String xquantity=decimalFormat.format(l.getQuantity());
                                        String total = decimalFormat.format(l.getTotal());
                                        String priceID = l.getLine() + "-price";
                                    %>
                                    <td><input type="number" id="<%=priceID%>" value="<%=xformat%>" onkeyup="show_result('<%=priceID%>',<%=l.getLine()%>, '<%=l.getProduct().getId()%>')" required/></td>
                                    <td>
                                        <input type="number" id="<%=l.getLine()%>" value="<%=xquantity%>" onkeyup="show_result('<%=priceID%>',<%=l.getLine()%>, '<%=l.getProduct().getId()%>')" required/>
                                    </td>
                                    <td id="<%=l.getProduct().getId()%>"><%=total%></td>
                            <input type="hidden" id="<%=l.getProduct().getId()%>">

                            <td class="text-right"><button class="btn btn-sm btn-danger" value="<%=l.getProduct().getId()%>" onclick="get_delete_product('<%=priceID%>',<%=l.getLine()%>, '<%=l.getProduct().getId()%>')"><i class="fa fa-trash"></i> </button> </td>
                            </tr>
                            <%}%>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><strong>Tổng giá trị đơn hàng</strong></td>
                                <td class="text-right" id="total"><strong><%=xtotal%></strong></td>
                            <input type="hidden" value="<%=xtotal%>" id="xtotal">
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col mb-2">
                    <div class="row">
                        <div class="col-sm-12  col-md-6">
                        </div>
                        <div class="col-sm-12 col-md-6 text-right">
                            <button  class="btn btn-lg btn-block btn-success text-uppercase" onclick="getSubmit()">Thanh toán</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script language="javascript">

        function getSubmit() {
        <%if (order == null) {%>
            window.alert("Hóa đơn của bạn đang trống");
        <%} else {%>
            document.getElementById("zsubmit").value = 1;
            document.getElementById("deleteForm").submit();
        <%}%>
        }
        function get_delete_product(a, b, c) {

            document.getElementById("delete").value = c;
            var curentPrice = document.getElementById(a).value;
            var quantity = document.getElementById(b).value;
            document.getElementById(b + "u").value = quantity;
            document.getElementById(b + "p").value = curentPrice;
            document.getElementById("deleteForm").submit();
        }
        function show_result(a, b, c)
        {
            var curentPrice = document.getElementById(a).value;
            var quantity = document.getElementById(b).value;
            document.getElementById(b + "u").value = quantity;
            document.getElementById(b + "p").value = curentPrice;
            var div = document.getElementById(c);
            var total = document.getElementById("total");
            var n = quantity * curentPrice;
            var value = n.toLocaleString(
                    undefined, {minimumFractionDigits: 0}
            );
            div.innerHTML = value;
            var xtotal = 0;
        <%if (order != null) {%>
        <% for (OrderLine l : order.getLines()) {%>

            var replaceTotal = document.getElementById("<%=l.getProduct().getId()%>").innerHTML;
            while (replaceTotal.indexOf(",") != -1) {
                replaceTotal = replaceTotal.replace(",", "");
            }
            xtotal = xtotal + parseFloat(replaceTotal, 10);
        <%}%>
        <%} else {%>

        <%}%>
            var xvalue = xtotal.toLocaleString(
                    undefined, {minimumFractionDigits: 0}
            );
            total.innerHTML = xvalue;
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>

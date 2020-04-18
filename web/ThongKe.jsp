<%-- 
    Document   : ThongKe
    Created on : Nov 11, 2019, 4:47:23 PM
    Author     : Darkness_King
--%>

<%@page import="connectData.getData"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entity.xThongKe"%>
<%@page import="ulti.HtmlHelper"%>
<%@page import="controller.ThongKe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
        <%
            ArrayList<xThongKe> tk = (ArrayList<xThongKe>) request.getAttribute("ThongKe");
            Integer pageindex = (Integer) request.getAttribute("pageindex");
            Integer pagecount = (Integer) request.getAttribute("pagecount");
            String xdate = (String) session.getAttribute("xdate");
            String ztype = (String) session.getAttribute("ztype");
            String todate=(String) session.getAttribute("todate");
            DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");
            int type = (Integer) session.getAttribute("type");

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
                        <form action="statistical" method="GET">
                            <select  style="width: 300px;height: 50px;margin: 10px;" name="typeTK" onchange="this.form.submit()">
                                <option>Chọn kiểu thống kê </option>
                                <option value="0"> Thống kê theo ngày</option>
                                <option value="2">Thống kê theo tuần</option>
                                <option value="1">Thống kê theo tháng</option>
                               
                            </select>
                        </form>
                    </div>

                </nav>
            </div>
        </div>
        <%if (type == 0) {%>
        <div style="margin-left: 20px">
            <h3>Chọn ngày thống kê:</h3>
            <form action="statistical" method="GET">
                <input type="date" name="date">
                <input type ="submit" value="Thống kê">
                <input type="hidden" name="typeTK" value="0">
            </form>
        </div>
        <%} else if (type == 1) {%>
        <div style="margin-left: 20px">
            <h3>Chọn Tháng Thống Kê:</h3>
            <form action="statistical" method="GET">
                <input type="number" pattern="[1-12]" name="date">
                <input type ="submit" value="Thống kê">
                <input type="hidden" name="typeTK" value="1">
            </form>
        </div>
        <%} else if (type == 2) {%>
        <div style="margin-left: 20px">
            <h3>Chọn Tuần Thống Kê:</h3>
            <form action="statistical" method="GET">
                From <input type="date"  name="date">
                To <input type="date"  name="xdate">
                <input type ="submit" value="Thống kê">
                <input type="hidden" name="typeTK" value="2">
            </form>
        </div>
        <%}%>

        <div style="margin-left: 20px">
            <%if (tk != null) {%>
            <% float lai = (Float) session.getAttribute("lai");%>
            <div><h1> Tiền Lãi <%=decimalFormat.format(lai)%>,000</h1> </div>
            <div class="table-responsive">
                <table class="table">
                    <%int a = -1;%>
                    <tr>
                        <td><h6>Tên Sản Phẩm</h6></td>
                        <td><h6>Giá</h6></td>
                        <td><h6>Số lượng</h6></td>
                        <td><h6>Thành Tiền</h6></td>
                    </tr>
                    <%float total = 0;%>
                    <%for (xThongKe xKe : tk) {%>
                    <tr>
                        <td>
                            <%=xKe.getProductName()%>
                        </td>
                        <td>
                            <%=decimalFormat.format(xKe.getProductPrice())%>
                        </td>
                        <td>
                            <%=decimalFormat.format(xKe.getQuantity())%>
                        </td>
                        <td>
                            <%=decimalFormat.format(xKe.thanhTien())%>

                        </td>
                    </tr>



                    <%}%>



                </table>
                <%if (type != 2) {%>
                <div>    <%=HtmlHelper.pagerTK(pageindex, pagecount, 2, xdate, ztype)%></div>
                <%}else{%>
                <div>
                    <%=HtmlHelper.spagerTK(pageindex, pagecount, 2, xdate, todate, ztype)%>
                    
                </div>
                <%}%>
            </div>
            <%}%>
        </div>
    </body>
</html>



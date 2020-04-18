<%-- 
    Document   : checkOut
    Created on : Oct 12, 2019, 4:30:45 PM
    Author     : Darkness_King
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="entity.OrderLine"%>
<%@page import="entity.Order"%>
<%@page import="entity.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <%
            ArrayList<Customer> ar = (ArrayList<Customer>) request.getAttribute("listCus");
            Order order = (Order) session.getAttribute("cart");
            DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");
            String xtotal = decimalFormat.format(order.getTotal());
            Customer cus = (Customer) request.getAttribute("Cus");
            Integer size = (Integer) session.getAttribute("tempsize");
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
                </nav>
            </div>
            <div class="row" style="margin-left: ">
                <div class="col-md-8">
                    <div class="container-fluid text-left" style="background-color: #c12c2c; margin-left: 15px"> <h1 align="center">Hóa Đơn của bạn</h1></div>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <td>ID Sản phẩm</td>
                            <td>Tên sản phẩm</td>
                            <td>Giá</td>
                            <td>Số lượng</td>
                            <td>Thành tiền</td>
                            </thead>
                            <tbody>
                                <% if (order == null) { %>

                                <%} else {%>
                                <tr>
                                    <% for (OrderLine l : order.getLines()) {%>
                                    <td><%=l.getProduct().getId()%></td>
                                    <td><%=l.getProduct().getName()%></td>
                                    <%
                                        String xformat = decimalFormat.format(l.getPrice());
                                        String xquantity=decimalFormat.format(l.getQuantity());
                                        String total = decimalFormat.format(l.getTotal());
                                        String priceID = l.getLine() + "-price";
                                    %>
                                    <td><%=xformat%></td>
                                    <td><%=xquantity%>
                                    </td>
                                    <td><%=total%></td>
                                </tr>
                                <%}%>
                                <tr>
                                    <td colspan="4">Tổng giá trị đơn hàng</td>
                                    <td id="total"><%=xtotal%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-4">
                    <div>
                        <form action="checkOut" method="POST">
                            <select  style="width: 300px;height: 50px;margin: 10px;" name="customer" onchange="this.form.submit()">
                                <option>Khách hàng</option>
                                <option value="1">Khách vãn lai</option>
                                <option value="new">Khách mới</option>
                                <%for (Customer c : ar) {%>
                                <%if (c.getId() != 1) {%>
                                <option value="<%=c.getId()%>"><%=c.getName()%></option>
                                <%}%>
                                <%}%> 
                            </select>
                        </form>
                    </div>
                    <%if (cus != null) {%>
                    <%if (cus.getName() != null) {%>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <td>Tên khách hàng</td>
                             <%if(cus.getId() !=1){%>
                            <td>Địa chỉ</td>
                            <td>Số điện thoại</td> 
                            <%}%>
                            <td>Tổng giá trị đơn hàng</td>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%=cus.getName()%></td>
                                     <%if(cus.getId() !=1){%>
                                    <td><%=cus.getAddress()%></td>
                                    <td>0<%=cus.getPhone()%></td> 
                                    <%}%>
                                    
                                    <td id="total"><%=xtotal%></td>
                                </tr>
                                <tr>
                                    <td>
                                        <form action="insertController" id="insert" method="POST">
                                            <input type="hidden" value="<%=cus.getId()%>" name="xcusId">
                                            <button class="btn btn-primary btn-lg" type="submit" onclick="display()">Thanh Toán</button>
                                        </form>
                                    </td>
                                    <%if(cus.getId() !=1){%>
                                    <td>
                                        <form action="insertTempController" id="getcusID">
                                            <input type="hidden" value="<%=cus.getId()%>" name="xcusId">
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Đặt Trước</button>

                                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Nhập thông tin</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">

                                                            <div class="form-group">
                                                                <h3>Thời gian giao hàng</h3>
                                                                <input type="date" class="form-control" id="recipient-name" name="ordate" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <h3>Ghi chú</h3>
                                                                <input type="text" class="form-control" id="recipient-name" name="Note">
                                                            </div>

                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                            <button  class="btn btn-primary" type="submit" onclick="displayTemp()">Thanh toán</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </td>
                                    <%}%>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <%} else {%>
                    <div class="table-responsive">
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <form action="insertController" method="POST">  
                                            <input type="hidden" value="-1" name="xcusId">
                                            <table>
                                                <tr>
                                                    <td><input type="text" name="customerName"  id="customerName" placeholder="CustomerName" required></td>
                                                    <td><input type="text" name="customerAddress" id="customerAddress" placeholder="Address" required></td>
                                                    <td><input type="number" name="customerPhone" id="customerPhone" placeholder="Phone" required></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">Total:</td>
                                                    <td id="total"><%=xtotal%></td>
                                                </tr>
                                                <tr>
                                                    <td> <button type="submit" class="btn btn-primary btn-lg" onclick="display()">Thanh Toán</button></td>
                                                </tr>
                                            </table>
                                        </form>
                                    </td>                                                
                                </tr>
                                <tr>
                                    <td>
                                        <form action="insertTempController" id="xinsert">
                                            <table>
                                                <td>  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"  onclick="get()">Đặt Trước</button></td>
                                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Nhập thông tin</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">

                                                                <div class="form-group">
                                                                    <h3>Thời gian giao hàng</h3>
                                                                    <input type="date" class="form-control" id="recipient-name" name="ordate" required>
                                                                </div>
                                                                <div class="form-group">
                                                                    <h3>Ghi chứ</h3>
                                                                    <input type="text" class="form-control" id="recipient-name" name="Note">
                                                                </div>
                                                                <input type="hidden" name="customerName" id="xcustomerName">
                                                                <input type="hidden" name="customerAddress" id="xcustomerAddress">
                                                                <input type="hidden" name="customerPhone" id="xcustomerPhone">
                                                            </div>
                                                            <div class="modal-footer">  
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                                <button type="submit"  class="btn btn-primary" onclick="displayTemp()">Thanh toán</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                           
                                            </table>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>




                    <%}%>
                    <%}%>


                    <div id="1234"></div>

                </div>
            </div>
        </div>
        <script  language="javascript">
          function display(){
              window.alert("Thanh toán thành công");
          }    
          function displayTemp(){
              window.alert("Đã thêm vào đơn đặt trước");
          }
    
        function get() {
                document.getElementById("xcustomerName").value = document.getElementById("customerName").value;
                document.getElementById("xcustomerAddress").value = document.getElementById("customerAddress").value;
                document.getElementById("xcustomerPhone").value = document.getElementById("customerPhone").value;
            }

        </script>
    </body>



    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>

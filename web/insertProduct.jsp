<%-- 
    Document   : insertProduct
    Created on : Nov 11, 2019, 6:26:31 PM
    Author     : Darkness_King
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    </head>
    <body>

        <div>
            <div >
                <nav class="nav">
                    <div class="container">
                        <form action="getProduct">
                            <button type="submit" class="btn btn-primary">Trang chủ</button>  
                        </form>
                    </div>
                </nav>
            </div>
        </div>

        <div class="container">
            <form action="insertProduct" method="POST" id="7">
                <div class="row">
                    <div>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>ID sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá nhập</th>
                                        <th>Giá bán</th>
                                        <th>Số lượng</th>
                                        <th>Ghi chú</th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody> 
                                    <tr>   
                                        <td>
                                            <input type="text" id="1"  name ="ProductID" placeholder="ID Sản phẩm" required>
                                        </td>
                                        <td>
                                            <input  type="text" id="2" name ="ProductName" placeholder="Tên Sản phẩm" required>
                                        </td>
                                        <td>
                                            <input  type="number"  id="3"  name ="CostPrice" placeholder="Giá nhập" required>
                                        </td>
                                        <td>
                                            <input   type="number" id="4" name ="SellingPrice" placeholder="Giá bán" required>
                                        </td>
                                        <td>
                                            <input type="number" id="5" name ="Quantity" placeholder="Số lượng" required>
                                        </td>
                                        <td>
                                            <input type="text"  id="6" name ="Note" placeholder="Ghi chú">
                                        </td>
                                    </tr>
                                    <tr>   
                                        <td>
                                            <button type="submit" onclick="check()">Thêm sản phẩm</button>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> 
            </form>
        </div>
        <script>
            function check() {

                // alert(document.getElementById("1").value);
                if (document.getElementById("1").value == "" || document.getElementById("2").value == "" || document.getElementById("3").value == "" || document.getElementById("4").value == "" ||document.getElementById("5").value == "") {
                    alert("Vui lòng điền đầy đủ thông tin");
                }else{
                    alert("Thêm sản phẩm thành công");
                    document.getElementById("7").submit();
                }
            }

        </script>


    </body>
</html>

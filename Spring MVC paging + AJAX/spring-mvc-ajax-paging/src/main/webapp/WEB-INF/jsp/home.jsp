<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Spring MVC + Ajax Paging demo</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="/resources/js/jquery.twbsPagination.min.js"/>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>


        <div class="container">
            <h2>jQuery Paging + Ajax Demo</h2>
            <h2>Data</h2>
            <table class="table" id="dataTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>ISBN</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${bookList}" var="book">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.name}</td>
                            <td>${book.author}</td>
                            <td>${book.price}</td>
                            <td>${book.isbn}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination" id="pagination"></ul>
            </nav>
        </div>
        <script type="text/javascript">
            $(function () {
                window.pagObj = $('#pagination').twbsPagination({
                    totalPages: 20,
                    visiblePages: 7
                }).on('page', function (event, page) {
                    $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "/book/" + page,
                        //data : comment,
                        timeout : 100000,
                        success : function(data) {
                            console.log(data);
                            display(data);
                        }
                    });
                });

                function display(data) {
                    $("#dataTable tbody").html(data);
                }
            });
        </script>
    </body>
</html>

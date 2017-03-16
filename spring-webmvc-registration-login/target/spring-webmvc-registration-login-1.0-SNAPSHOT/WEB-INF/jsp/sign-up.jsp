<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Spring-SignUp</title>

        <%--Bootstrap CSS--%>
        <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <%--CSS--%>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

        <link rel="stylesheet" href="/resources/font/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="/resources/css/form-elements.css">
        <link rel="stylesheet" href="/resources/css/style.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <!-- Top content -->
        <div class="top-content">

            <div class="inner-bg">
                <div class="container">

                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>Bootstrap</strong> Login &amp; Register Forms</h1>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-5">
                            <div class="form-box">
                                <div class="form-top">
                                    <div class="form-top-left">
                                        <h3>Login to our site</h3>
                                        <p>Enter username and password to log on:</p>
                                    </div>
                                    <div class="form-top-right">
                                        <i class="fa fa-lock"></i>
                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <c:if test="${errorSignIn != null}">
                                        <div class="alert alert-danger">
                                            <c:out value="${errorSignIn}"/>
                                        </div>
                                    </c:if>
                                    <form role="form" action="sign-in" method="post" class="login-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="form-username">Email (*)</label>
                                            <input type="email" name="form-username" placeholder="Email..." class="form-username form-control" id="form-username" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="form-password">Password (*)</label>
                                            <input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="form-password" required>
                                        </div>
                                        <button type="submit" class="btn">Sign in!</button>
                                    </form>
                                </div>
                            </div>

                            <div class="social-login">
                                <h3>...or login with:</h3>
                                <div class="social-login-buttons">
                                    <a class="btn btn-link-2" href="#">
                                        <i class="fa fa-facebook"></i> Facebook
                                    </a>
                                    <a class="btn btn-link-2" href="#">
                                        <i class="fa fa-twitter"></i> Twitter
                                    </a>
                                    <a class="btn btn-link-2" href="#">
                                        <i class="fa fa-google-plus"></i> Google Plus
                                    </a>
                                </div>
                            </div>

                        </div>

                        <div class="col-sm-1 middle-border"></div>
                        <div class="col-sm-1"></div>

                        <div class="col-sm-5">

                            <div class="form-box">
                                <div class="form-top">
                                    <div class="form-top-left">
                                        <h3>Sign up now</h3>
                                        <p>Fill in the form below to get instant access:</p>
                                    </div>
                                    <div class="form-top-right">
                                        <i class="fa fa-pencil"></i>
                                    </div>
                                </div>
                                <div class="form-bottom">
                                    <c:if test="${errorSignUp != null}">
                                        <div class="alert alert-danger">
                                            <c:out value="${errorSignUp}"/>
                                        </div>
                                    </c:if>
                                    <form:form action="/sign-up" role="form" method="post" modelAttribute="user" class="registration-form">
                                        <div class="form-group">
                                            <label class="sr-only" for="form-full-name">Full name</label>
                                            <form:input path="fullName" type="text" name="form-full-name" class="form-full-name form-control" placeholder="Full name..." id="form-full-name"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="form-password">Password (*)</label>
                                            <form:input path="password" type="password" name="form-password" class="form-password form-control" placeholder="Password..." id="form-password" required="true"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="form-email">Email (*)</label>
                                            <form:input path="email" type="text" name="form-email" class="form-email form-control" placeholder="Email..." id="form-email" required="true"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="form-about-yourself">About yourself</label>
                                            <form:textarea path="aboutMe" name="form-about-yourself" class="form-about-yourself form-control" placeholder="About yourself..." id="form-about-yourself"/>
                                        </div>
                                        <button type="submit" class="btn">Sign me up!</button>
                                    </form:form>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>

        </div>

        <!-- Footer -->
        <footer>
            <div class="container">
                <div class="row">

                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="footer-border"></div>
                        <p>iViettech <i class="fa fa-smile-o"></i></p>
                    </div>

                </div>
            </div>
        </footer>



        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/resources/bootstrap/js/bootstrap.min.js"></script>

        <!-- Javascript -->
        <script src="/resources/js/jquery.backstretch.min.js"></script>
        <script src="/resources/js/scripts.js"></script>
    </body>
</html>
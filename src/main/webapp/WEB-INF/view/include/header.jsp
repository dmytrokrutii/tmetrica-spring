<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active"
                 style="background-image: url('http://images.vfl.ru/ii/1580386960/ad00a40d/29376144.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><spring:message
                            code="main.why"/></h2>
                    <p class="lead"><spring:message
                            code="main.firstSlide"/></p>
                </div>
            </div>
            <!-- Slide Two - Set the background image for this slide in the line below -->
            <div class="carousel-item"
                 style="background-image: url('http://images.vfl.ru/ii/1580387525/b1cd7742/29376186.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><spring:message
                            code="main.why"/></h2>
                    <p class="lead"><spring:message
                            code="main.secondSlide"/></p>
                </div>
            </div>
            <!-- Slide Three - Set the background image for this slide in the line below -->
            <div class="carousel-item"
                 style="background-image: url(' http://images.vfl.ru/ii/1580387589/ef1dbb8c/29376193.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h2 class="display-4"><spring:message
                            code="main.why"/></h2>
                    <p class="lead"><spring:message
                            code="main.thirdSlide"/></p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</header>
<%-- 
    Document   : courses
    Created on : May 29, 2017, 4:16:16 AM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-yellow">
        <div class="inner">
            <h3>Cafeterias</h3>

            <p>Information about Cafeterias</p>
        </div>
        <div class="icon">
            <i class="fa fa-building"></i>
        </div>
        <a href="${contextPath}/showCafetrias" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-olive">
        <div class="inner">
            <h3>Customers</h3>

            <p>Information about Customers </p>
        </div>
        <div class="icon">
            <i class="fa fa-male"></i>
        </div>
        <a href="${contextPath}/showallcustomers" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-aqua">
        <div class="inner">
            <h3>Categories</h3>

            <p>Information about Categories</p>
        </div>
        <div class="icon">
            <i class="fa fa-pie-chart"></i>
        </div>
        <a href="${contextPath}/showCategories" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-blue">
        <div class="inner">
            <h3>Items</h3>

            <p>Information about Items</p>
        </div>
        <div class="icon">
            <i class="fa fa-coffee"></i>
        </div>
        <a href="${contextPath}/showItem" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-red">
        <div class="inner">
            <h3>Offers</h3>

            <p>Information about Offers</p>
        </div>
        <div class="icon">
            <i class="fa fa-money"></i>
        </div>
        <a href="${contextPath}/showOffer" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-green">
        <div class="inner">
            <h3>Orders</h3>

            <p>Information about Orders</p>
        </div>
        <div class="icon">
            <i class="fa fa-file-text-o"></i>
        </div>
        <a href="${contextPath}/showAllOrders" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>
<!--
<div class="col-lg-4 col-xs-6">
     small box 
    <div class="small-box bg-fuchsia">
        <div class="inner">
            <h3>Row Materials</h3>

            <p>Information about Row Materials</p>
        </div>
        <div class="icon">
            <i class="fa fa-tags"></i>
        </div>
        <a href="${contextPath}/showRowMaterials" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>-->
<!--
<div class="col-lg-4 col-xs-6">
     small box 
    <div class="small-box bg-gray">
        <div class="inner">
            <h3>Stocks</h3>

            <p>Information about Stocks</p>
        </div>
        <div class="icon">
            <i class="fa fa-bank"></i>
        </div>
        <a href="${contextPath}/showStocks" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>-->

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-lime">
        <div class="inner">
            <h3>Workers</h3>

            <p>Information about Workers</p>
        </div>
        <div class="icon">
            <i class="fa fa-gears"></i>
        </div>
        <a href="${contextPath}/showallworkers" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-purple">
        <div class="inner">
            <h3>Privileges</h3>

            <p>Information about Privileges</p>
        </div>
        <div class="icon">
            <i class="fa  fa-legal"></i>
        </div>

        <a href="${contextPath}/showPrivileges" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>

    </div>
</div>

<div class="col-lg-4 col-xs-6">
    <!-- small box -->
    <div class="small-box bg-teal">
        <div class="inner">
            <h3>Roles</h3>

            <p>Information about Roles</p>
        </div>
        <div class="icon">
            <i class="fa fa-get-pocket"></i>
        </div>

        <a href="${contextPath}/showallroles" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>

    </div>
</div>

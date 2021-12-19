<%-- 
    Document   : slide
    Created on : May 24, 2017, 6:27:13 PM
    Author     : JEDiver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${contextPath}/resources/dist/img/user.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${sessionScope.user.name}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">OTHER NAVIGATION</li>
            <!--Backend-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-ravelry"></i>
                    <span>ITI Panel</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="http://localhost:8085/Backend View"><i class="fa fa-circle-o"></i>ITI Panel</a></li>
                </ul>
            </li>

            <li class="header">MAIN NAVIGATION</li>

            <!--caftria-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-building"></i>
                    <span>Cafeterias</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showCafetrias"><i class="fa fa-circle-o"></i>All Cafeterias</a></li>
                    <li><a href="addCafetria"><i class="fa fa-circle-o"></i>Add Cafeteria</a></li>
                </ul>
            </li>

            <!--customer-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-male"></i>
                    <span>Customers</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showallcustomers"><i class="fa fa-circle-o"></i>All Customers</a></li>
                </ul>
            </li>


            <!--category-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Categories</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showCategories"><i class="fa fa-circle-o"></i>All Categories</a></li>
                    <li><a href="addCategory"><i class="fa fa-circle-o"></i>Add Category</a></li>
                </ul>
            </li>


            <!--item-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-coffee"></i>
                    <span>Items</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showItem"><i class="fa fa-circle-o"></i>All Items</a></li>
                    <li><a href="addItem"><i class="fa fa-circle-o"></i>Add Item</a></li>
                </ul>
            </li>


            <!--offer-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-money"></i>
                    <span>Offers</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showAdminOffer"><i class="fa fa-circle-o"></i>All Offers</a></li>
                    <li><a href="addOffer"><i class="fa fa-circle-o"></i>Add Offer</a></li>
                </ul>
            </li>


            <!--order-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-file-text-o"></i>
                    <span>Orders</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showAllOrders"><i class="fa fa-circle-o"></i>All Orders</a></li>
                    <!--<li><a href="selectItems"><i class="fa fa-circle-o"></i>Add Order</a></li>-->
                </ul>
            </li>

<!--
            row material
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-tags"></i>
                    <span>Row Materials</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showRowMaterials"><i class="fa fa-circle-o"></i>All Row Materials</a></li>
                    <li><a href="addRowMaterial"><i class="fa fa-circle-o"></i>Add Row Material</a></li>
                </ul>
            </li>

            stock
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-bank"></i>
                    <span>Stocks</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showStocks"><i class="fa fa-circle-o"></i>All Stocks</a></li>
                    <li><a href="addStock"><i class="fa fa-circle-o"></i>Add Stock</a></li>
                </ul>
            </li>
-->
            <!--worker-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-gears"></i>
                    <span>Workers</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="showallworkers"><i class="fa fa-circle-o"></i>All Workers</a></li>
                </ul>
            </li>
            
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-legal"></i>
                    <span>Privileges</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">        
                        <li><a href="${contextPath}/showPrivileges"><i class="fa fa-circle-o"></i>Privileges</a></li>    
                        <li><a href="${contextPath}/addPrivilege"><i class="fa fa-circle-o"></i>Add Privileges</a></li>    
                </ul>
            </li>
            
             <li class="treeview">
                <a href="#">
                    <i class="fa fa-get-pocket"></i>
                    <span>Roles</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                        <li><a href="${contextPath}/showallroles"><i class="fa fa-circle-o"></i>Roles</a></li>    
                        <li><a href="${contextPath}/addRole"><i class="fa fa-circle-o"></i>Add Role</a></li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

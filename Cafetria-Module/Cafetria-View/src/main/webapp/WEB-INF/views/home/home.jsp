<%-- 
    Document   : home
    Created on : Jun 7, 2017, 2:27:47 PM
    Author     : atom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/layouts/includes.jsp"/>
<c:set var="cntxt" value="${pageContext.request.contextPath}"/>
<div class="button-order" onclick="selectedItems()"> Order Now </div>
<c:forEach items="${allItems}" var="item">
    <div class="col-md-4" style="margin-bottom: 2%;">
        <div class="food">
            <div class="cover img-responsive" style="background-image:url('${cntxt}/resources/img${item.id}.jpg');background-size: cover;">	
                <label>
                    <i>
                        <svg x="0px" y="0px" width="17px" height="17px">
                        <g>
                        <path d="M 7 1 C 7 1.5523 7.6716 2 8.5 2 C 9.3284 2 10 1.5523 10 1 C 10 0.4477 9.3284 0 8.5 0 C 7.6716 0 7 0.4477 7 1 ZM 7 2 C 7 2 0.6875 2.9375 0 7 C 0 7 -0.1875 9.6875 3 11 C 3 11 4.6875 10.9375 6 9 C 6 9 8.3125 12.8125 11 9 C 11 9 12.1875 10.875 14 11 C 14 11 16.75 10.0625 17 7 C 17 7 16.375 3 10.0625 1.9375 C 10.0625 1.9375 8.5 4.25 7 2 ZM 1 11 L 3 17 L 14 17 L 16 11 C 16 11 13.125 13 11 11 C 11 11 8.875 13.375 6 11 C 6 11 3 13.125 1 11 Z" fill="#ffffff"/>
                        </g>
                        </svg>
                    </i>
                    <font>${item.category.name}</font>
                </label>
            </div>
            <span class="order-selection selection"><input id="${item.id}" type="checkbox" class="checkboxes" /> </span>
            <span class="fav-selection selection" onclick="addToFav(${item.id});"><i id="fav-${item.id}" class="fa fa-star" aria-hidden="true"></i></span>
            <div class="info">
                <a href="#" class="recipe" class="btn btn-info btn-lg" data-toggle="modal" data-target="#detailsModal-${item.id}">
                    <i>
                        <svg x="0px" y="0px" width="26px" height="28px">
                        <g>
                        <path d="M 8.5 20 L 8.5 21 L 17.5 21 L 17.5 20 L 8.5 20 ZM 8.5 16 L 8.5 17 L 17.5 17 L 17.5 16 L 8.5 16 ZM 8.5 12 L 8.5 13 L 17.5 13 L 17.5 12 L 8.5 12 ZM 20 0 C 19.4477 0 19 0.4477 19 1 L 19 6 C 19 6.5523 19.4477 7 20 7 C 20.5523 7 21 6.5523 21 6 L 21 1 C 21 0.4477 20.5523 0 20 0 ZM 13 0 C 12.4477 0 12 0.4477 12 1 L 12 6 C 12 6.5523 12.4477 7 13 7 C 13.5523 7 14 6.5523 14 6 L 14 1 C 14 0.4477 13.5523 0 13 0 ZM 6 0 C 5.4477 0 5 0.4477 5 1 L 5 6 C 5 6.5523 5.4477 7 6 7 C 6.5523 7 7 6.5523 7 6 L 7 1 C 7 0.4477 6.5523 0 6 0 ZM 15 4 L 18 4 L 18 3 L 15 3 L 15 4 ZM 8 4 L 11 4 L 11 3 L 8 3 L 8 4 ZM 3 4 L 4 4 L 4 3 L 3 3 C 1.3431 3 0 4.3431 0 6 L 0 25 C 0 26.6569 1.3431 28 3 28 L 23 28 C 24.6569 28 26 26.6569 26 25 L 26 6 C 26 4.3431 24.6569 3 23 3 L 22 3 L 22 4 L 23 4 C 24.1046 4 25 4.8954 25 6 L 25 25 C 25 26.1046 24.1046 27 23 27 L 3 27 C 1.8954 27 1 26.1046 1 25 L 1 6 C 1 4.8954 1.8954 4 3 4 Z" fill="#ffffff"/>
                        </g>
                        </svg>
                    </i>
                    <font>Details</font>
                </a>
                <div class="content">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="140" rowspan="2" align="center" valign="middle">
                                <h2>${item.name}</h2>
                                <h4>${item.price} L.E</h4>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="detailsModal-${item.id}" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">Details</button>
                    <h4 class="modal-title">What's it made of?!</h4>
                </div>
                <div class="modal-body">
                    <i>${item.description}</i>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Delicious</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>


<!--<button onclick="selectedItems()"></button>-->
<a href="addOrder" hidden="true" id="addbutton"> </a>

<script>
    function addItem(row) {
        console.log("osama" + row);
        $.ajax({
            url: "/Cafetria/addItemToOrder?id=" + row
            ,
            type: 'GET'
            ,
            contentType: 'application/json'
            ,
            success: function (data, textStatus, jqXHR) {
                $('#addbutton')[0].click();
            }
            , error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown, textStatus, jqXHR);
            }
        });
    }

    function selectedItems() {
        var objects = document.getElementsByClassName("checkboxes");
        var arrValue = [];
        for (var i = 0; i < objects.length; i++) {
            if (objects[i].checked)
                arrValue.push(objects[i].id);
        }
        for (var i = 0; i < arrValue.length; i++) {
            console.log(arrValue[i]);
        }
		if(arrValue.length > 0)
			addItem(arrValue);
    }
    
   function removeFromFav(itemID){
       $.ajax({
            url: "/Cafetria/deleteItemToFav?id=" + itemID
            ,
            type: 'GET'
            ,
            contentType: 'application/json'
            ,
            success: function (data, textStatus, jqXHR) {
//                $('#addbutton')[0].click();
                    console.log("halhala ya fawaz  "+itemID );
            }
            , error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown, textStatus, jqXHR);
            }
        });
       
   }

    function addToFav(itemId) {
        var itemIdArr = [itemId];
        $.ajax({
            url: "${cntxt}/addItemToFav?id=" + itemIdArr
            ,
            type: 'GET'
            ,
            contentType: 'application/json'
            ,
            success: function (data, textStatus, jqXHR) {
                var star = document.getElementById("fav-" + itemId);
                if (star.style.color == 'yellow') {
                    star.style.color = 'white';
                    removeFromFav(itemId);
                } else {
                    star.style.color = 'yellow';
                }
            }
            , error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown, textStatus, jqXHR);
            }
        });
    }
</script>


<%@ page import="com.ninja.game.*" %>
<%--
  Created by IntelliJ IDEA.
  User: sleepwalker
  Date: 21.03.2020
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
    <link rel="stylesheet" href="game.css">
</head>
<body>
    <% Map map = (Map)request.getAttribute("map"); %>
    <% Hero hero = map.getHero(); %>
    <% for (int y = 0; y < Map.HEIGHT; y++) { %>
        <div class="row">
        <% for (int x = 0; x < Map.WIDTH; x++) { %>
            <% Element el = map.getElement(x, y); %>
            <% String htmlClass = hero.canSee(x, y) ? "visible" : "fog"; %>
            <div class="cell <%= htmlClass %> <%= el.getCSS() %>" title="<%= el.getHoverName() %>">
                <%= el.getSymbol() %>
                <%if(hero.x == x && hero.y == y) { %>
                <div class="cell Hero <%= el.getCSS() %>" title="<%= el.getHoverName() %>">
                    <%= hero.getSymbol() %>
                </div>
                <% } %>
            </div>
        <% } %>
        </div>
    <% } %>

    <form method="post">

     <div class="controls">
        <button class="left" type="submit" name="direction" value="left">⇦</button>
        <button class="right" type="submit" name="direction" value="right">⇨</button>
        <button class="top" type="submit" name="direction" value="top">⇧</button>
        <button class="down" type="submit" name="direction" value="down">⇩</button>
     </div>



     <div class="notification">
        <% for(String message : map.notification.getNotification()) { %>
            <span class="good_notification">
                <%= message %>
            </span>
        <% } %>
        <br>
        <% for(String message : map.notification.getBadNotification()) { %>
            <span class="bad_notification">
                <%= message %>
            </span>
        <% } %>
     </div>
     <div class="buff">
         <% for(UsedItems it : map.buff.getBuffList()) { %>
             <span class="buff">
                 <% if (it.time > 0) {%>
                    <%= it.slot.getName() %>: <%= it.time %>
                 <% } %>
             </span>
         <% } %>

     </div>
     <div class="health_bar">
         <span class="health">Health: <%= hero.curHealth %> / <%= hero.health %></span><br>
         <span class="health">Damage: <%= hero.calculateBaseDamage() %> </span><br>
         <span class="money">Money: <%= hero.money %></span><br>
         <span class="lvl">Lvl: <%= hero.lvl %></span><br>
         <span class="exp">Exp: <%= hero.exp %> / 100</span><br>
         <span class="armor">Armor: <%= hero.armor %></span>
     </div>

    <div class="inventory">
        <% int n = 1; %>
        <% for(int i = 0; i < hero.items.size(); i ++) { %>
            <div class="slot">
                <span><%= n++ %></span>
                <span class="symbol"><%= hero.items.get(i).getName() %></span>
                <span class="description"><%= hero.items.get(i).getDescription() %></span>
                <button type="submit" name="delete" value="<%= i %>">Delete</button>
                <% if (hero.items.get(i).heal() > 0) {%>
                    <% if (hero.items.get(i).getID() == 666) {%>
                        <button type="submit" name="vodka" value="<%= i %>">Use</button>
                    <% } else {%>
                        <button type="submit" name="heal" value="<%= i %>">Use</button>
                    <% } %>
                <% } %>
            </div>
        <% } %>
    </div>


    <div class="seller_inventory">
        <% n = 1; %>
        <% NPC seller = map.getNPC(hero); %>
        <% if (seller != null) {%>
            <%if (seller.equipment() != null) {%>
        <table>
                <tr>
                    <td>№</td>
                    <td>Price</td>
                    <td>Name of equipment</td>
                </tr>
                <% for (Equipment e: seller.equipment()) {%>
                <tr>
                    <td>
                        <%= n++ %>
                    </td>
                    <td>
                        $<%= e.price %>
                    </td>
                    <td>
                        <%= e.name %>
                    </td>
                    <td>
                        <button type="submit" name="buy" value="<%= e.slot.getID() %>">Buy</button>
                    </td>
                </tr>
                <%}%>
            </table>
        <%}%>
        <% if (seller.hisList() != null) {%>
        <table>
            <% for (String s : seller.hisList()) {%>
            <tr>
                <td>
                    -<%= s %>
                </td>
            </tr>
            <%}%>
        </table>
        <%}%>
        <%}%>
    </div>
    <div class="enemy_stats">
        <%Enemy enemy = map.getEnemy(hero); %>
        <% if (enemy != null) {%>
        <table>
            <tr>
                <td>Health</td>
                <td>Damage</td>
            </tr>
            <tr>
                <td>
                    <%= enemy.getName() %>
                </td>
                <td>
                    ♡<%= enemy.health() %>
                </td>
                <td>
                    ⚔<%= enemy.damage() %>
                </td>
                <td>
                    <button type="submit" name="attack" value="<%= 1 %>">Attack</button>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
    </form>

    <script>
        function press(selector) {
            document.querySelector(selector).click();
        }

        window.addEventListener('keydown', function(event) {
            if (event.code === 'KeyW') {
                press('.top');
            } else if (event.code === 'KeyA') {
                press('.left');
            } else if (event.code === 'KeyS') {
                press('.down');
            } else if (event.code === 'KeyD') {
                press('.right');
            }
        });
    </script>

</body>
</html>

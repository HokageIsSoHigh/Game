import com.ninja.game.LoadMap;
import com.ninja.game.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static Map map;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String direction = request.getParameter("direction");
        String delete = request.getParameter("delete");
        String buy = request.getParameter("buy");
        String attack = request.getParameter("attack");
        String useHeal = request.getParameter("heal");
        String drinkVodka = request.getParameter("vodka");

        if (direction != null && direction != "") {
            map.move(direction);
        }
        if (delete != null && delete != "") {
            map.getHero().deleteSlot(Integer.parseInt(delete));
        }
        if (buy != null && buy != ""){
            map.getHero().heroBuy(map.getNPC(map.getHero()), Integer.parseInt(buy));
        }
        if (attack != null && attack != ""){
            map.battle();
        }
        if (useHeal != null && useHeal != "") {
            map.getHero().drinkingHealthPotion(map.getHero().getSlot(map.getHero().items.get(Integer.parseInt(useHeal)).getID()).heal(), Integer.parseInt(useHeal));
        }
        if (drinkVodka != null && drinkVodka != "") {
            map.getHero().addBuff(10, map.getHero().items.get(Integer.parseInt(drinkVodka)), Integer.parseInt(drinkVodka));
        }

        response.sendRedirect("/Game_war_exploded/controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (map == null) {
            createMap();
        }

        if (request.getQueryString() != null && request.getQueryString().contains("restart")) {
            createMap();
        }
        if (map.getHero().curHealth <= 0) {
            renderGameOver(request, response);
        }else{
            renderGame(request, response);
        }
    }

    private void renderGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("map", map);
        getServletContext().getRequestDispatcher("/game.jsp").forward(request, response);
    }

    private void renderGameOver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Game_war_exploded/");
    }

    private void createMap() {
        map = new Map();
        LoadMap.loadMap(map, getServletContext().getResourceAsStream("/map.csv"));
    }
}

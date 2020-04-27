import com.ninja.game.LoadMap;
import com.ninja.game.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map map = (Map) session.getAttribute("map");

        String direction = request.getParameter("direction");
        String delete = request.getParameter("delete");
        String drop = request.getParameter("drop");
        String buy = request.getParameter("buy");
        String attack = request.getParameter("attack");
        String useHeal = request.getParameter("heal");
        String drinkVodka = request.getParameter("vodka");
        String escape = request.getParameter("escape");

        if (direction != null && direction != "") {
            map.move(direction);
        }
        if (delete != null && delete != "") {
            map.getHero().deleteSlot(Integer.parseInt(delete));
        }
        if (drop != null && drop != "") {
            map.getHero().dropSlot(Integer.parseInt(drop), map);
        }
        if (buy != null && buy != "") {
            map.getHero().heroBuy(map.getNPC(map.getHero()), Integer.parseInt(buy));
        }
        if (attack != null && attack != "") {
            map.battle();
        }
        if (useHeal != null && useHeal != "") {
            map.getHero().drinkingHealthPotion(map.getHero().getSlot(map.getHero().items.get(Integer.parseInt(useHeal)).getID()).heal(), Integer.parseInt(useHeal));
        }
        if (drinkVodka != null && drinkVodka != "") {
            map.getHero().addBuff(10, map.getHero().items.get(Integer.parseInt(drinkVodka)), Integer.parseInt(drinkVodka));
        }
        if (escape != null && escape != "") {
            map.getHero().escape();
        }

        response.sendRedirect("/Game_war_exploded/controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map map = (Map) session.getAttribute("map");
        if (map == null) {
            map = createMap();
            session.setAttribute("map", map);
        }
        if (request.getQueryString() != null && request.getQueryString().contains("restart")) {
            map = createMap();
            session.setAttribute("map", map);
        }
        if (map.getHero().curHealth <= 0) {
            renderGameOver(request, response);
        } else {
            renderGame(request, response, map);
        }
    }

    private void renderGame(HttpServletRequest request, HttpServletResponse response, Map map) throws ServletException, IOException {
        request.setAttribute("map", map);
        getServletContext().getRequestDispatcher("/game.jsp").forward(request, response);
    }

    private void renderGameOver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Game_war_exploded/");
    }

    private Map createMap() {
        Map map = new Map();
        LoadMap.loadMap(map, getServletContext().getResourceAsStream("/map.csv"));
        return map;
    }
}

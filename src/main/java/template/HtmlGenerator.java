package template;

import model.Advertisement;

import java.util.List;

// Deprecated Class

public class HtmlGenerator {

    public static  String generateHtml(List<Advertisement> advertisements) {
        StringBuilder html = new StringBuilder();
        String top = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Advertisement</title>
                    <link href="/css/index.css" rel="stylesheet">
                </head>
                <body>
                <div id="main">
                    <form action="/ad" method="post">
                        <h3>Create Advertisement</h3>
                        <label for="name">Name: </label>
                        <input id="name" name="name" type="text"><br><br>
                        <label for="model">Model: </label>
                        <input id="model" name="model" type="text"><br><br>
                        <label for="cost">Cost: </label>
                        <input id="cost" name="cost" type="number" min="0"><br><br>
                        <label for="url">Img: </label>
                        <input id="url" name="url" type="text"><br><br>
                        <button type="submit">Save</button>
                    </form>
                </div>
                <div class="list">
        """;
        html.append(top);
        if (!advertisements.isEmpty()) {
            for (int i = 0; i < advertisements.size(); i++) {
                if (i!=0 && i%6==0){
                    html.append("</div>\n<div class=\"list\">");
                }
                Advertisement ad = advertisements.get(i);
                String listString = """
                            <div class="element">
                                <p id="name-%d">Name: %s</p>
                                <p id="model-%d">Model: %s</p>
                                <p id="cost-%d">Cost: %s </p>
                                <form action="/ad/delete/%d" method="post" onsubmit="return confirm('Are you sure you want to delete this item?');">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <button id="delete-%d"type="submit">Delete Ad</button>
                                </form>
                                <img id="url-%d" class="images" src="%s" alt="Wrong URL of car photo"></img>
                            </div>
                        """;
                html.append(String.format(listString,
                        ad.getId(), ad.getName(),
                        ad.getId(), ad.getModel(),
                        ad.getId(), ad.getCost(),
                        ad.getId(), ad.getId(),
                        ad.getId(), ad.getUrl()));
            }
        } else {
            String empty = """
                    <div class="empty">
                    <h3> DataBase is empty.</h3>
                    </div>
                    """;
            html.append(empty);
        }
        String bottom = """
                </div>
                </body>
                </html>
                """;
        html.append(bottom);

        return html.toString();
    }
}

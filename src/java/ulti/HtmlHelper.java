/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

/**
 *
 * @author sonnt
 */
public class HtmlHelper {

    public static String hyperlink(String label, String href) {
        return "<li class=\"page-item\"> <a class=\"page-link\" href=\"" + href + "\">" + label + "</a></li>";
    }

    public static String pager(int pageindex, int pagecount, int gap) {

        String result = "<nav style=\"margin-left: 700px\">\n"
                + "  <ul class=\"pagination\">";
        if (pageindex > gap + 1) {
            result += hyperlink("First", "getOrderTemp?page=" + 1);
        }

        for (int i = gap; i > 0; i--) {
            if (pageindex - gap >= 0) {
                if (pageindex - i > 0) {
                    result += hyperlink("" + (pageindex - i), "getOrderTemp?page=" + (pageindex - i));
                }
            }
        }

        result += " <li class=\"page-item active\" aria-current=\"page\">\n"
                + "      <a class=\"page-link\" href=\"#\">" + pageindex + " <span class=\"sr-only\">(current)</span></a>\n"
                + "    </li>";
        boolean a = true;
        for (int i = 1; i <= gap; i++) {
            if (pageindex + gap <= pagecount) {
                result += hyperlink("" + (pageindex + i), "getOrderTemp?page=" + (pageindex + i));
            } else if (pageindex < pagecount && pageindex + gap >= pagecount && a == true) {
                result += hyperlink("" + pagecount, "getOrderTemp?page=" + pagecount);
                a = false;
            }
        }
        if (pageindex + gap < pagecount - 1) {
            result += hyperlink("Last", "getOrderTemp?page=" + pagecount);
        }
        result += " </ul>\n"
                + "</nav>";

        return result;
    }

    public static String pagerTK(int pageindex, int pagecount, int gap, String date, String typeTK) {

        String result = "<nav style=\"margin-left: 700px\">\n"
                + "  <ul class=\"pagination\">";
        if (date.trim().compareToIgnoreCase("null") != 0) {
            if (pageindex > gap + 1) {
                result += hyperlink("First", "statistical?date="+date+"&typeTK="+typeTK+"&page=" + 1);
            }

            for (int i = gap; i > 0; i--) {
                if (pageindex - gap >= 0) {
                    if (pageindex - i > 0) {
                        result += hyperlink("" + (pageindex - i), "statistical?date="+date+"&typeTK="+typeTK+"&page=" + (pageindex - i));
                    }
                }
            }

            result += " <li class=\"page-item active\" aria-current=\"page\">\n"
                    + "      <a class=\"page-link\" href=\"#\">"+pageindex+"<span class=\"sr-only\">(current)</span></a>\n"
                    + "    </li>";
            boolean a = true;
            for (int i = 1; i <= gap; i++) {
                if (pageindex + gap <= pagecount) {
                    result += hyperlink("" + (pageindex + i), "statistical?date="+date+"&typeTK="+typeTK+"&page=" + (pageindex + i));
                } else if (pageindex < pagecount && pageindex + gap >= pagecount && a == true) {
                    result += hyperlink("" + pagecount, "statistical?date="+date+"&typeTK="+typeTK+"&page=" + pagecount);
                    a = false;
                }
            }
            if (pageindex + gap < pagecount - 1) {
                result += hyperlink("Last", "statistical?date=" + date + "&typeTK=" + typeTK + "&page=" + pagecount);
            }
            result += " </ul>\n"
                    + "</nav>";

        }
        return result;
    }
    
    
    
    
    
      public static String spagerTK(int pageindex, int pagecount, int gap, String date,String todate, String typeTK) {

        String result = "<nav style=\"margin-left: 700px\">\n"
                + "  <ul class=\"pagination\">";
        if (date.trim().compareToIgnoreCase("null") != 0) {
            if (pageindex > gap + 1) {
                result += hyperlink("First", "statistical?date="+date+"&xdate="+todate+"&typeTK="+typeTK+"&page=" + 1);
            }

            for (int i = gap; i > 0; i--) {
                if (pageindex - gap >= 0) {
                    if (pageindex - i > 0) {
                        result += hyperlink("" + (pageindex - i), "statistical?date="+date+"&xdate="+todate+"&typeTK="+typeTK+"&page=" + (pageindex - i));
                    }
                }
            }

            result += " <li class=\"page-item active\" aria-current=\"page\">\n"
                    + "      <a class=\"page-link\" href=\"#\">"+pageindex+"<span class=\"sr-only\">(current)</span></a>\n"
                    + "    </li>";
            boolean a = true;
            for (int i = 1; i <= gap; i++) {
                if (pageindex + gap <= pagecount) {
                    result += hyperlink("" + (pageindex + i), "statistical?date="+date+"&xdate="+todate+"&typeTK="+typeTK+"&page=" + (pageindex + i));
                } else if (pageindex < pagecount && pageindex + gap >= pagecount && a == true) {
                    result += hyperlink("" + pagecount, "statistical?date="+date+"&xdate="+todate+"&typeTK="+typeTK+"&page=" + pagecount);
                    a = false;
                }
            }
            if (pageindex + gap < pagecount - 1) {
                result += hyperlink("Last", "statistical?date="+date+"&xdate="+todate+"&typeTK="+typeTK+"&page=" + pagecount);
            }
            result += " </ul>\n"
                    + "</nav>";

        }
        return result;
    }
}

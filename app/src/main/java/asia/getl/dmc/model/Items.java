package asia.getl.dmc.model;

public class Items {

    private String title;
   private String redvalue,yvalue,gvalue,gpsvalue;


    public Items(String title, String redvalue, String yvalue, String gvalue, String gpsvalue) {
        this.title = title;
        this.redvalue = redvalue;
        this.yvalue = yvalue;
        this.gvalue = gvalue;
        this.gpsvalue = gpsvalue;
    }

    public String getYvalue() {
        return yvalue;
    }

    public void setYvalue(String yvalue) {
        this.yvalue = yvalue;
    }

    public String getGvalue() {
        return gvalue;
    }

    public void setGvalue(String gvalue) {
        this.gvalue = gvalue;
    }

    public String getGpsvalue() {
        return gpsvalue;
    }

    public void setGpsvalue(String gpsvalue) {
        this.gpsvalue = gpsvalue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRedvalue() {
        return redvalue;
    }

    public void setRedvalue(String redvalue) {
        this.redvalue = redvalue;
    }
}

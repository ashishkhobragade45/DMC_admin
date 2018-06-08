package asia.getl.dmc.model;

public class Showreport {

    String day;

    private long st_red,st_yellow,st_green,st_nongps;


    public Showreport(String day, long st_red, long st_yellow, long st_green, long st_nongps) {
        this.day = day;
        this.st_red = st_red;
        this.st_yellow = st_yellow;
        this.st_green = st_green;
        this.st_nongps = st_nongps;
    }

    public Showreport() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public long getSt_red() {
        return st_red;
    }

    public void setSt_red(long st_red) {
        this.st_red = st_red;
    }

    public long getSt_yellow() {
        return st_yellow;
    }

    public void setSt_yellow(long st_yellow) {
        this.st_yellow = st_yellow;
    }

    public long getSt_green() {
        return st_green;
    }

    public void setSt_green(long st_green) {
        this.st_green = st_green;
    }

    public long getSt_nongps() {
        return st_nongps;
    }

    public void setSt_nongps(long st_nongps) {
        this.st_nongps = st_nongps;
    }
}

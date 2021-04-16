package ReportSet;

//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ResourceCDN;
import cucumber.api.cli.Main;

        import java.text.SimpleDateFormat;
import java.util.Date;

public class BeforeSet {

    /*public static void sendReportText(String content, ExtentColor color) {
        ExtentTest test = getReportLogger();
        Markup m = MarkupHelper.createLabel(content, color);
        test.log(Status.DEBUG, m);
    }*/

    public static String getReport(){
        try {

            //ExtentReports extent = new ExtentReports();
            //extent.setGherkinDialect("zh-CN");//解决地方语言问题
            SimpleDateFormat fmt  = new SimpleDateFormat("yyyy&MM&dd HH-mm-ss-SSS"); //精确到毫秒
            String suffix = fmt.format(new Date());

            /*ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target\\report\\report.html");
            htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);*/


            //System.setProperty("extent.reporter.html.start", "true");
            //System.setProperty("extent.reporter.html.out", "test-output\\extentReport\\HtmlReport.html");
            //System.setProperty("extent.reporter.html.config", "D:\\DownloadFiles\\Idea-files\\koala\\src\\main\\resources\\extent-config.xml");

            return "yes";
        } catch (Exception e) {
            e.printStackTrace();
            return "nope";
        }
    }

    public static void cuEntry() throws Throwable {

            Main.main(new String[]{"--glue",
                    "JavaSteps",
                    "--plugin",
                    "rerun:target/rerun.txt",
                    "--plugin",
                    "pretty",
                    "--plugin",
                    "html:test-output\\cucumberReport",
                    "--plugin",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    /*"--plugin",
                    "CucumberReport.ExtentCucumberAdapter:test-output",*/
                    "src/main/resources/features/ManagerApi.feature"});//"src\\main\\resources\\features\\ManagerApi.feature"

    }

    public static void main(String[] args) throws Throwable {
        BeforeSet.getReport();
        BeforeSet.cuEntry();

        
    }


        /*System.setProperty("extent.reporter.html.out", "G:\\UI_Test_src\\ApiGroup\\target\\extent-report\\1.html");
        String[] args1 = new String[5];
        args1[0] = "-g";
        args1[1] = "HttpFeature";
        *//*args1[2] = "-p";
        args1[3] = "html:target/1.html";*//*
        *//*args1[4] = "-p";
        args1[5] = "pretty";*//*
        args1[2] = "-p";
        args1[3] = "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/1.html";
        *//*args1[5] = "rerun:target/rerun.txt";
        args1[6] = "-t";
        args1[7] = "@123";
        args1[8] = "-t";
        args1[9] = tag;*//*
        args1[4] = "classpath:HttpFeature/eg.feature";
        return args1;*/



    /*html 自带的html
    * eg：
    –no-source 不显示对应的文件名和行号
    –format pretty 美化窗口输出
    –format html 生成一个好看的html报告
    –out 写入文件或目录
    *
    *
    * */
}


package vn.nlu.cnpm.until;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class sendMail {
    private static String mail = "thanhloi12a1.transuyen@gmail.com";
    private static String pass = "thanhloi";
    private String to, subject, body;

    public sendMail(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }




    public void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress(mail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(this.subject, "UTF-8");
            message.setContent(this.body, "text/html");
            Transport.send(message);
            System.out.println("message sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMailRegister(String to, String key) {
        String link = "https://cnpm2020.azurewebsites.net/active?email=" + to + "&key=" + key ;
        System.out.println(link);
        String body = "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "      xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n" +
                "    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n" +
                "\n" +
                "\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- CSS Reset : BEGIN -->\n" +
                "    <style>\n" +
                "\n" +
                "        html,\n" +
                "        body {\n" +
                "            margin: 0 auto !important;\n" +
                "            padding: 0 !important;\n" +
                "            height: 100% !important;\n" +
                "            width: 100% !important;\n" +
                "            background: #f1f1f1;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Stops email clients resizing small text. */\n" +
                "        * {\n" +
                "            -ms-text-size-adjust: 100%;\n" +
                "            -webkit-text-size-adjust: 100%;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Centers email on Android 4.4 */\n" +
                "        div[style*=\"margin: 16px 0\"] {\n" +
                "            margin: 0 !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Stops Outlook from adding extra spacing to tables. */\n" +
                "        table,\n" +
                "        td {\n" +
                "            mso-table-lspace: 0pt !important;\n" +
                "            mso-table-rspace: 0pt !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Fixes webkit padding issue. */\n" +
                "        table {\n" +
                "            border-spacing: 0 !important;\n" +
                "            border-collapse: collapse !important;\n" +
                "            table-layout: fixed !important;\n" +
                "            margin: 0 auto !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Uses a better rendering method when resizing images in IE. */\n" +
                "        img {\n" +
                "            -ms-interpolation-mode: bicubic;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\n" +
                "        a {\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: A work-around for email clients meddling in triggered links. */\n" +
                "        *[x-apple-data-detectors], /* iOS */\n" +
                "        .unstyle-auto-detected-links *,\n" +
                "        .aBn {\n" +
                "            border-bottom: 0 !important;\n" +
                "            cursor: default !important;\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "            font-size: inherit !important;\n" +
                "            font-family: inherit !important;\n" +
                "            font-weight: inherit !important;\n" +
                "            line-height: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\n" +
                "        .a6S {\n" +
                "            display: none !important;\n" +
                "            opacity: 0.01 !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Prevents Gmail from changing the text color in conversation threads. */\n" +
                "        .im {\n" +
                "            color: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        /* If the above doesn't work, add a .g-img class to any image in question. */\n" +
                "        img.g-img + div {\n" +
                "            display: none !important;\n" +
                "        }\n" +
                "\n" +
                "        /* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n" +
                "        /* Create one of these media queries for each additional viewport size you'd like to fix */\n" +
                "\n" +
                "        /* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\n" +
                "        @media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\n" +
                "            u ~ div .email-container {\n" +
                "                min-width: 320px !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        /* iPhone 6, 6S, 7, 8, and X */\n" +
                "        @media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n" +
                "            u ~ div .email-container {\n" +
                "                min-width: 375px !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        /* iPhone 6+, 7+, and 8+ */\n" +
                "        @media only screen and (min-device-width: 414px) {\n" +
                "            u ~ div .email-container {\n" +
                "                min-width: 414px !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "    <!-- CSS Reset : END -->\n" +
                "\n" +
                "    <!-- Progressive Enhancements : BEGIN -->\n" +
                "    <style>\n" +
                "\n" +
                "        .primary {\n" +
                "            background: #f3a333;\n" +
                "        }\n" +
                "\n" +
                "        .bg_white {\n" +
                "            background: #ffffff;\n" +
                "        }\n" +
                "\n" +
                "        .bg_light {\n" +
                "            background: #fafafa;\n" +
                "        }\n" +
                "\n" +
                "        .bg_black {\n" +
                "            background: #000000;\n" +
                "        }\n" +
                "\n" +
                "        .bg_dark {\n" +
                "            background: rgba(0, 0, 0, .8);\n" +
                "        }\n" +
                "\n" +
                "        .email-section {\n" +
                "            padding: 2.5em;\n" +
                "        }\n" +
                "\n" +
                "        /*BUTTON*/\n" +
                "        .btn {\n" +
                "            padding: 10px 15px;\n" +
                "        }\n" +
                "\n" +
                "        .btn.btn-primary {\n" +
                "            border-radius: 30px;\n" +
                "            background: #f3a333;\n" +
                "            color: #ffffff;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        h1, h2, h3, h4, h5, h6 {\n" +
                "            font-family: 'Playfair Display', serif;\n" +
                "            color: #000000;\n" +
                "            margin-top: 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "            font-weight: 400;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 1.8;\n" +
                "            color: rgba(0, 0, 0, .4);\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #f3a333;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "        }\n" +
                "\n" +
                "        /*LOGO*/\n" +
                "\n" +
                "        .logo h1 {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .logo h1 a {\n" +
                "            color: #000;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: 700;\n" +
                "            text-transform: uppercase;\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        /*HERO*/\n" +
                "        .hero {\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .hero img {\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .hero .text {\n" +
                "            color: rgba(255, 255, 255, .8);\n" +
                "        }\n" +
                "\n" +
                "        .hero .text h2 {\n" +
                "            color: #ffffff;\n" +
                "            font-size: 30px;\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        /*HEADING SECTION*/\n" +
                "        .heading-section {\n" +
                "        }\n" +
                "\n" +
                "        .heading-section h2 {\n" +
                "            color: #000000;\n" +
                "            font-size: 28px;\n" +
                "            margin-top: 0;\n" +
                "            line-height: 1.4;\n" +
                "        }\n" +
                "\n" +
                "        .heading-section .subheading {\n" +
                "            margin-bottom: 20px !important;\n" +
                "            display: inline-block;\n" +
                "            font-size: 13px;\n" +
                "            text-transform: uppercase;\n" +
                "            letter-spacing: 2px;\n" +
                "            color: rgba(0, 0, 0, .4);\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .heading-section .subheading::after {\n" +
                "            position: absolute;\n" +
                "            left: 0;\n" +
                "            right: 0;\n" +
                "            bottom: -10px;\n" +
                "            content: '';\n" +
                "            width: 100%;\n" +
                "            height: 2px;\n" +
                "            background: #f3a333;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "\n" +
                "        .heading-section-white {\n" +
                "            color: rgba(255, 255, 255, .8);\n" +
                "        }\n" +
                "\n" +
                "        .heading-section-white h2 {\n" +
                "            font-size: 28px;\n" +
                "            /*font-family: padding-bottom: 0;*/\n" +
                "        }\n" +
                "\n" +
                "        .heading-section-white h2 {\n" +
                "            color: #ffffff;\n" +
                "        }\n" +
                "\n" +
                "        .heading-section-white .subheading {\n" +
                "            margin-bottom: 0;\n" +
                "            display: inline-block;\n" +
                "            font-size: 13px;\n" +
                "            text-transform: uppercase;\n" +
                "            letter-spacing: 2px;\n" +
                "            color: rgba(255, 255, 255, .4);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        .icon {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .icon img {\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        /*SERVICES*/\n" +
                "        .text-services {\n" +
                "            padding: 10px 10px 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .text-services h3 {\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        /*BLOG*/\n" +
                "        .text-services .meta {\n" +
                "            text-transform: uppercase;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "\n" +
                "        /*TESTIMONY*/\n" +
                "        .text-testimony .name {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .text-testimony .position {\n" +
                "            color: rgba(0, 0, 0, .3);\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        /*VIDEO*/\n" +
                "        .img {\n" +
                "            width: 100%;\n" +
                "            height: auto;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .img .icon {\n" +
                "            position: absolute;\n" +
                "            top: 50%;\n" +
                "            left: 0;\n" +
                "            right: 0;\n" +
                "            bottom: 0;\n" +
                "            margin-top: -25px;\n" +
                "        }\n" +
                "\n" +
                "        .img .icon a {\n" +
                "            display: block;\n" +
                "            width: 60px;\n" +
                "            position: absolute;\n" +
                "            top: 0;\n" +
                "            left: 50%;\n" +
                "            margin-left: -25px;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        /*COUNTER*/\n" +
                "        .counter-text {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .counter-text .num {\n" +
                "            display: block;\n" +
                "            color: #ffffff;\n" +
                "            font-size: 34px;\n" +
                "            font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        .counter-text .name {\n" +
                "            display: block;\n" +
                "            color: rgba(255, 255, 255, .9);\n" +
                "            font-size: 13px;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        /*FOOTER*/\n" +
                "\n" +
                "        .footer {\n" +
                "            color: rgba(255, 255, 255, .5);\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .footer .heading {\n" +
                "            color: #ffffff;\n" +
                "            font-size: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .footer ul {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        .footer ul li {\n" +
                "            list-style: none;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .footer ul li a {\n" +
                "            color: rgba(255, 255, 255, 1);\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        @media screen and (max-width: 500px) {\n" +
                "\n" +
                "            .icon {\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "\n" +
                "            .text-services {\n" +
                "                padding-left: 0;\n" +
                "                padding-right: 20px;\n" +
                "                text-align: left;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "    </style>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;\">\n" +
                "<center style=\"width: 100%; background-color: #f1f1f1;\">\n" +
                "    <div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n" +
                "        &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\n" +
                "    </div>\n" +
                "    <div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">\n" +
                "        <!-- BEGIN BODY -->\n" +
                "        <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\"\n" +
                "               style=\"margin: auto;\">\n" +
                "            <tr>\n" +
                "                <td valign=\"middle\" class=\"hero\"\n" +
                "                    style=\"background-image: url(https://i.ibb.co/hgFRq8n/bg-1.jpg); background-size: cover; height: 400px;\">\n" +
                "                    <table>\n" +
                "                        <tr>\n" +
                "                            <td>\n" +
                "                                <div class=\"text\" style=\"padding: 0 3em; text-align: center;\">\n" +
                "                                    <h2>We Serve Healthy &amp; Organic Foods</h2>\n" +
                "                                    <p>Click in <a href=\""+link+"\">"+link+"</a> to active or.</p>\n" +
                "                                    <p  ><a  href=\""+ link +"\" class=\"btn btn-primary\">Active your account Here!</a></p>\n" +
                "                                </div>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr><!-- end tr -->\n" +
                "            <tr>\n" +
                "                <td class=\"bg_white\">\n" +
                "                    <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                "                        <tr>\n" +
                "                            <td class=\"bg_dark email-section\" style=\"text-align:center;\">\n" +
                "                                <div class=\"heading-section heading-section-white\">\n" +
                "                                    <span class=\"subheading\">Welcome</span>\n" +
                "                                    <h2>Welcome To TheGioiRauSach</h2>\n" +
                "                                    <p>A small river named Duden flows by their place and supplies it with the necessary\n" +
                "                                        regelialia. It is a paradisematic country, in which roasted parts of sentences\n" +
                "                                        fly into your mouth.</p>\n" +
                "                                </div>\n" +
                "                            </td>\n" +
                "\n" +
                "                    </table>\n" +
                "\n" +
                "                </td>\n" +
                "            </tr><!-- end:tr -->\n" +
                "            <!-- 1 Column Text + Button : END -->\n" +
                "        </table>\n" +
                "\n" +
                "    </div>\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";
//    System.out.println(body);
        sendMail e = new sendMail(to, "Wellcome to OrganicFood", body);
        e.sendMail();

    }


    public static void main(String[] args) {
        String link = "href=\"localhost:8080/active?email=" + 1 + "&key=" + 2 + "\"";
        System.out.println(link);

        sendMailRegister("vip.thanhloi@gmail.com", "316089");
    }
}

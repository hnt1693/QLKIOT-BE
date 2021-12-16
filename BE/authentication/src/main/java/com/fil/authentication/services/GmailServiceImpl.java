package com.fil.authentication.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GmailServiceImpl implements GmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendActiveMail(Account account) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String htmlMsg = "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js\"></script>\n" +
                "<div id=\"CONTACT\">\n" +
                "    <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "    <!-- Generator: Adobe Illustrator 23.0.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->\n" +
                "    <svg version=\"1.1\" id=\"Mail\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"\n" +
                "         viewBox=\"0 0 1366 768\" style=\"enable-background:new 0 0 1366 768;\" xml:space=\"preserve\">\n" +
                "<style type=\"text/css\">\n" +
                "\t.stmail0{fill:#E6E6E6;}\n" +
                "    .stmail1{fill:#808080;}\n" +
                "    .stmail2{fill:#FAF0CA;}\n" +
                "    .stmail3{enable-background:new    ;}\n" +
                "    .stmail4{fill:#666666;}\n" +
                "    .stmail5{fill:#B3B3B3;}\n" +
                "    .stmail6{opacity:0.96;fill:#B3B3B3;enable-background:new    ;}\n" +
                "</style>\n" +
                "        <g id=\"MailBody\">\n" +
                "\t<path id=\"Flap\" class=\"stmail0\" d=\"M666.9,167.1L467,292h432L698.9,167c-9.7-6.1-22.1-6.1-31.9,0L467,292h432L699.1,167.1\n" +
                "\t\tC689.3,160.9,676.7,160.9,666.9,167.1z\"/>\n" +
                "            <path id=\"Inside\" class=\"stmail1\" d=\"M666.9,416.9L467,292h432L698.9,417c-9.7,6.1-22.1,6.1-31.9,0L467,292h432L699.1,416.9\n" +
                "\t\tC689.3,423.1,676.7,423.1,666.9,416.9z\"/>\n" +
                "            <g id=\"NoteB\">\n" +
                "\t\t<path id=\"NoteBaseForward_1_\" class=\"stmail2\" d=\"M851.4,415H514.6c-6.4,0-11.6-5.2-11.6-11.6V231.6c0-6.4,5.2-11.6,11.6-11.6h336.7\n" +
                "\t\t\tc6.4,0,11.6,5.2,11.6,11.6v171.7C863,409.8,857.8,415,851.4,415z\"/>\n" +
                "                <g id=\"CONTACTletters\" class=\"stmail3\">\n" +
                "\t\t\t<path class=\"stmail4\" d=\"M528.7,334.8c-3.9-2.1-6.9-5-9.1-8.6c-2.2-3.7-3.3-7.8-3.3-12.5c0-4.6,1.1-8.8,3.3-12.5s5.2-6.6,9.1-8.6\n" +
                "\t\t\t\tc3.9-2.1,8.2-3.1,13-3.1c4.1,0,7.7,0.7,11,2.1s6,3.5,8.3,6.2l-7,6.4c-3.2-3.7-7.1-5.5-11.8-5.5c-2.9,0-5.5,0.6-7.8,1.9\n" +
                "\t\t\t\ts-4.1,3-5.3,5.3c-1.3,2.3-1.9,4.9-1.9,7.8s0.6,5.5,1.9,7.8c1.3,2.3,3,4.1,5.3,5.3c2.3,1.3,4.9,1.9,7.8,1.9\n" +
                "\t\t\t\tc4.7,0,8.6-1.9,11.8-5.6l7,6.4c-2.2,2.7-5,4.8-8.3,6.2c-3.3,1.4-7,2.1-11.1,2.1C536.9,337.9,532.6,336.8,528.7,334.8z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M576.9,334.7c-3.9-2.1-7-5-9.2-8.7s-3.3-7.8-3.3-12.4s1.1-8.7,3.3-12.4c2.2-3.7,5.3-6.6,9.2-8.7\n" +
                "\t\t\t\ts8.3-3.1,13.2-3.1s9.2,1,13.1,3.1c3.9,2.1,6.9,5,9.2,8.7c2.2,3.7,3.4,7.8,3.4,12.4s-1.1,8.7-3.4,12.4c-2.2,3.7-5.3,6.6-9.2,8.7\n" +
                "\t\t\t\ts-8.3,3.1-13.1,3.1C585.2,337.9,580.8,336.8,576.9,334.7z M597.6,326.7c2.2-1.3,4-3,5.3-5.3s1.9-4.9,1.9-7.8s-0.6-5.5-1.9-7.8\n" +
                "\t\t\t\tc-1.3-2.3-3-4.1-5.3-5.3c-2.2-1.3-4.7-1.9-7.5-1.9s-5.3,0.6-7.5,1.9s-4,3-5.3,5.3s-1.9,4.9-1.9,7.8s0.6,5.5,1.9,7.8\n" +
                "\t\t\t\tc1.3,2.3,3,4.1,5.3,5.3c2.2,1.3,4.7,1.9,7.5,1.9C592.8,328.6,595.3,328,597.6,326.7z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M666.9,290.2v46.9H658l-23.4-28.5v28.5h-10.7v-46.9h9l23.3,28.5v-28.5H666.9z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M687.7,299h-15v-8.8h40.9v8.8h-15v38.1h-10.9V299z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M748,327h-21.8l-4.2,10h-11.1l20.9-46.9h10.7l21,46.9h-11.4L748,327z M744.6,318.8l-7.4-18l-7.4,18H744.6z\"\n" +
                "                    />\n" +
                "                    <path class=\"stmail4\" d=\"M777.2,334.8c-3.9-2.1-6.9-5-9.1-8.6c-2.2-3.7-3.3-7.8-3.3-12.5c0-4.6,1.1-8.8,3.3-12.5s5.2-6.6,9.1-8.6\n" +
                "\t\t\t\tc3.9-2.1,8.2-3.1,13-3.1c4.1,0,7.7,0.7,11,2.1s6,3.5,8.3,6.2l-7,6.4c-3.2-3.7-7.1-5.5-11.8-5.5c-2.9,0-5.5,0.6-7.8,1.9\n" +
                "\t\t\t\ts-4.1,3-5.3,5.3c-1.3,2.3-1.9,4.9-1.9,7.8s0.6,5.5,1.9,7.8c1.3,2.3,3,4.1,5.3,5.3c2.3,1.3,4.9,1.9,7.8,1.9\n" +
                "\t\t\t\tc4.7,0,8.6-1.9,11.8-5.6l7,6.4c-2.2,2.7-5,4.8-8.3,6.2s-7,2.1-11.1,2.1C785.4,337.9,781.1,336.8,777.2,334.8z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M826.1,299h-15v-8.8H852v8.8h-15v38.1h-10.9V299z\"/>\n" +
                "\t\t</g>\n" +
                "\t</g>\n" +
                "            <path id=\"Base\" class=\"stmail0\" d=\"M699.1,416.9L699.1,416.9l-0.2,0.1c-9.7,6.1-22.1,6.1-31.9,0l-0.2-0.1l0,0L467,292v203.8\n" +
                "\t\tc0,16.7,13.5,30.2,30.2,30.2h371.3c16.8,0,30.5-13.6,30.5-30.5V292L699.1,416.9z\"/>\n" +
                "            <g id=\"NoteF\">\n" +
                "\t\t<path id=\"NoteBaseBackward\" class=\"stmail2\" d=\"M851.4,415H514.6c-6.4,0-11.6-5.2-11.6-11.6V231.6c0-6.4,5.2-11.6,11.6-11.6h336.7\n" +
                "\t\t\tc6.4,0,11.6,5.2,11.6,11.6v171.7C863,409.8,857.8,415,851.4,415z\"/>\n" +
                "                <g id=\"ContactInfo\">\n" +
                "\t\t\t<g id=\"SocialGroup\">\n" +
                "\t\t\t\t<g id=\"SocialMedia\">\n" +
                "\t\t\t\t\t<g>\n" +
                "\t\t\t\t\t\t<circle id=\"twitter\" class=\"stmail2\" cx=\"740.2\" cy=\"365.9\" r=\"17.5\"/>\n" +
                "                        <path  class=\"stmail5\" d=\"M740.2,348.4c-9.7,0-17.5,7.8-17.5,17.5s7.8,17.5,17.5,17.5s17.5-7.8,17.5-17.5S749.9,348.4,740.2,348.4z\n" +
                "\t\t\t\t\t\t\t M748.9,363.1c0,0.1,0,0.3,0,0.5c0,5.6-4.2,11.9-11.9,11.9c-2.4,0-4.6-0.7-6.4-1.8c0.3,0.1,0.6,0.1,1,0.1\n" +
                "\t\t\t\t\t\t\tc1.9,0,3.8-0.7,5.2-1.8c-1.8,0-3.4-1.2-3.9-2.9c0.3,0.1,0.5,0.1,0.8,0.1c0.4,0,0.7-0.1,1.1-0.2c-1.9-0.4-3.3-2.1-3.3-4.1\n" +
                "\t\t\t\t\t\t\tc0-0.1,0-0.1,0-0.1c0.5,0.3,1.1,0.5,1.8,0.5c-1.1-0.7-1.8-2-1.8-3.4c0-0.8,0.1-1.5,0.5-2.1c2.1,2.6,5.2,4.2,8.6,4.4\n" +
                "\t\t\t\t\t\t\tc-0.1-0.4-0.1-0.6-0.1-1c0-2.3,1.8-4.1,4.1-4.1c1.3,0,2.3,0.5,3.1,1.3c0.9-0.3,1.8-0.7,2.6-1.1c-0.3,1-0.9,1.8-1.8,2.3\n" +
                "\t\t\t\t\t\t\tc0.8-0.1,1.6-0.2,2.4-0.6C750.4,361.9,749.7,362.6,748.9,363.1z\"/>\n" +
                "\t\t\t\t\t</g>\n" +
                "                    <g>\n" +
                "\t\t\t\t\t\t<rect id=\"linkd\" x=\"664.7\" y=\"350.3\" class=\"stmail2\" width=\"31.1\" height=\"31.1\"/>\n" +
                "                        <path class=\"stmail5\" d=\"M694.6,348.4H666c-1.7,0-3.2,1.4-3.2,3.2v28.6c0,1.7,1.4,3.2,3.2,3.2h28.6c1.7,0,3.2-1.4,3.2-3.2v-28.6\n" +
                "\t\t\t\t\t\t\tC697.7,349.8,696.3,348.4,694.6,348.4z M673.6,378.6c0,0.5-0.4,0.9-0.9,0.9h-3.9c-0.5,0-0.9-0.4-0.9-0.9v-16.4\n" +
                "\t\t\t\t\t\t\tc0-0.5,0.4-0.9,0.9-0.9h3.9c0.5,0,0.9,0.4,0.9,0.9V378.6z M670.7,359.7c-2.1,0-3.7-1.7-3.7-3.7c0-2.1,1.7-3.7,3.7-3.7\n" +
                "\t\t\t\t\t\t\ts3.7,1.7,3.7,3.7C674.4,358.1,672.8,359.7,670.7,359.7z M694,378.7c0,0.5-0.4,0.9-0.9,0.9h-4.2c-0.5,0-0.9-0.4-0.9-0.9V371\n" +
                "\t\t\t\t\t\t\tc0-1.1,0.3-5-3-5c-2.6,0-3.1,2.6-3.2,3.8v8.9c0,0.5-0.4,0.9-0.9,0.9h-4c-0.5,0-0.9-0.4-0.9-0.9v-16.5c0-0.5,0.4-0.9,0.9-0.9h4\n" +
                "\t\t\t\t\t\t\tc0.5,0,0.9,0.4,0.9,0.9v1.5c1-1.5,2.4-2.6,5.4-2.6c6.7,0,6.7,6.3,6.7,9.7L694,378.7L694,378.7z\"/>\n" +
                "\t\t\t\t\t</g>\n" +
                "                    <g>\n" +
                "\t\t\t\t\t\t<path id=\"igram\" class=\"stmail2\" d=\"M629.3,382h-17.2c-4.7,0-8.6-3.8-8.6-8.6v-15.2c0-4.7,3.8-8.6,8.6-8.6h17.2c4.7,0,8.6,3.8,8.6,8.6v15.2\n" +
                "\t\t\t\t\t\t\tC637.9,378.2,634.1,382,629.3,382z\"/>\n" +
                "                        <g>\n" +
                "\t\t\t\t\t\t\t<path class=\"stmail5\" d=\"M627.3,348.4h-14.1c-5.7,0-10.4,4.7-10.4,10.4V373c0,5.7,4.7,10.4,10.4,10.4h14.1\n" +
                "\t\t\t\t\t\t\t\tc5.7,0,10.4-4.7,10.4-10.4v-14.1C637.7,353.1,633.1,348.4,627.3,348.4z M634.2,372.9c0,3.8-3.1,6.9-6.9,6.9h-14.1\n" +
                "\t\t\t\t\t\t\t\tc-3.8,0-6.9-3.1-6.9-6.9v-14.1c0-3.8,3.1-6.9,6.9-6.9h14.1c3.8,0,6.9,3.1,6.9,6.9V372.9L634.2,372.9z\"/>\n" +
                "                            <path class=\"stmail5\" d=\"M620.2,356.9c-5,0-9,4-9,9s4,9,9,9s9-4,9-9S625.2,356.9,620.2,356.9z M620.2,371.4c-3,0-5.5-2.5-5.5-5.5\n" +
                "\t\t\t\t\t\t\t\ts2.5-5.5,5.5-5.5s5.5,2.5,5.5,5.5S623.3,371.4,620.2,371.4z\"/>\n" +
                "                            <circle class=\"stmail5\" cx=\"629.3\" cy=\"356.9\" r=\"2.2\"/>\n" +
                "\t\t\t\t\t\t</g>\n" +
                "\t\t\t\t\t</g>\n" +
                "                    <g>\n" +
                "\t\t\t\t\t\t<rect  id=\"fbook\" x=\"784.6\" y=\"350.3\" class=\"stmail2\" width=\"31.1\" height=\"31.1\"/>\n" +
                "                        <path class=\"stmail6\" d=\"M813,348.4h-25.6c-2.6,0-4.7,2.1-4.7,4.7v25.6c0,2.6,2.1,4.7,4.7,4.7H800v-12.5h-3.2\n" +
                "\t\t\t\t\t\t\tc-0.4,0-0.7-0.4-0.7-0.7v-4c0-0.4,0.4-0.8,0.7-0.8h3.2v-3.9c0-4.5,2.8-7,6.8-7h3.3c0.4,0,0.7,0.4,0.7,0.7v3.4\n" +
                "\t\t\t\t\t\t\tc0,0.4-0.4,0.7-0.7,0.7h-2c-2.2,0-2.6,1.1-2.6,2.6v3.4h4.8c0.4,0,0.8,0.4,0.7,0.9l-0.5,4c0,0.4-0.4,0.7-0.7,0.7h-4.3v12.5h7.5\n" +
                "\t\t\t\t\t\t\tc2.6,0,4.7-2.1,4.7-4.7v-25.6C817.8,350.5,815.7,348.4,813,348.4z\"/>\n" +
                "\t\t\t\t\t</g>\n" +
                "                    <g>\n" +
                "\t\t\t\t\t\t<path  id=\"github\" class=\"stmail2\" d=\"M576.8,360.3c-2.2-7.3-8.9-12.7-16.7-12.7c-9.7,0-17.5,8-17.5,17.9c0,2.1,0.4,4.1,1,6\n" +
                "\t\t\t\t\t\t\tc2.2,7.3,8.9,12.7,16.7,12.7c9.7,0,17.5-8,17.5-17.9C577.8,364.2,577.5,362.2,576.8,360.3z\"/>\n" +
                "                        <path class=\"stmail5\" d=\"M560.2,348.4c-9.7,0-17.5,8-17.5,17.9c0,7.9,5,14.6,11.9,17c0.9,0.2,1.2-0.4,1.2-0.9c0-0.4,0-1.6,0-3.1\n" +
                "\t\t\t\t\t\t\tc-4.9,1.1-5.9-2.4-5.9-2.4c-0.8-2.1-1.9-2.6-1.9-2.6c-1.6-1.1,0.1-1.1,0.1-1.1c1.7,0.1,2.7,1.9,2.7,1.9\n" +
                "\t\t\t\t\t\t\tc1.5,2.8,4.1,1.9,5.1,1.5c0.1-1.1,0.6-1.9,1.1-2.4c-3.9-0.5-8-2-8-8.8c0-2,0.7-3.5,1.8-4.8c-0.2-0.5-0.8-2.3,0.1-4.7\n" +
                "\t\t\t\t\t\t\tc0,0,1.4-0.5,4.8,1.8c1.4-0.4,2.9-0.6,4.4-0.6s3,0.2,4.4,0.6c3.3-2.3,4.8-1.8,4.8-1.8c0.9,2.4,0.3,4.3,0.2,4.7\n" +
                "\t\t\t\t\t\t\tc1.1,1.2,1.8,2.9,1.8,4.8c0,6.9-4.1,8.4-8,8.8c0.6,0.5,1.2,1.6,1.2,3.3c0,2.4,0,4.3,0,4.9c0,0.5,0.3,1,1.2,0.8\n" +
                "\t\t\t\t\t\t\tc7-2.3,12-9.1,12-17C577.6,356.4,569.8,348.4,560.2,348.4z\"/>\n" +
                "\t\t\t\t\t</g>\n" +
                "\t\t\t\t</g>\n" +
                "                <g class=\"stmail3\">\n" +
                "\t\t\t\t\t<path class=\"stmail4\" d=\"M542.6,333.7c-0.9-0.3-1.6-0.6-2.1-1l0.9-2.1c0.5,0.4,1.1,0.7,1.8,0.9c0.7,0.2,1.4,0.3,2.1,0.3\n" +
                "\t\t\t\t\t\tc0.8,0,1.4-0.1,1.7-0.3c0.4-0.2,0.6-0.5,0.6-0.9c0-0.3-0.1-0.5-0.3-0.7s-0.5-0.3-0.8-0.5s-0.8-0.2-1.4-0.4\n" +
                "\t\t\t\t\t\tc-0.9-0.2-1.6-0.4-2.2-0.6c-0.6-0.2-1.1-0.6-1.5-1c-0.4-0.5-0.6-1.1-0.6-1.9c0-0.7,0.2-1.3,0.6-1.9c0.4-0.6,0.9-1,1.7-1.3\n" +
                "\t\t\t\t\t\ts1.7-0.5,2.8-0.5c0.8,0,1.5,0.1,2.2,0.3c0.7,0.2,1.4,0.4,1.9,0.8l-0.8,2.1c-1.1-0.6-2.2-0.9-3.3-0.9c-0.8,0-1.3,0.1-1.7,0.4\n" +
                "\t\t\t\t\t\tc-0.4,0.2-0.6,0.6-0.6,1s0.2,0.7,0.6,0.9c0.4,0.2,1.1,0.4,1.9,0.6c0.9,0.2,1.6,0.4,2.2,0.6c0.6,0.2,1.1,0.6,1.5,1\n" +
                "\t\t\t\t\t\ts0.6,1.1,0.6,1.9c0,0.7-0.2,1.3-0.6,1.9c-0.4,0.6-0.9,1-1.7,1.3s-1.7,0.5-2.8,0.5C544.4,334.1,543.5,334,542.6,333.7z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M553.8,333.4c-0.8-0.4-1.4-1-1.8-1.7c-0.4-0.7-0.6-1.5-0.6-2.4s0.2-1.7,0.6-2.4s1-1.3,1.8-1.7\n" +
                "\t\t\t\t\t\tc0.8-0.4,1.6-0.6,2.6-0.6s1.8,0.2,2.6,0.6s1.4,1,1.8,1.7s0.6,1.5,0.6,2.4s-0.2,1.7-0.6,2.4c-0.4,0.7-1,1.3-1.8,1.7\n" +
                "\t\t\t\t\t\tc-0.8,0.4-1.6,0.6-2.6,0.6C555.5,334,554.6,333.8,553.8,333.4z M558.1,331.2c0.4-0.5,0.7-1.1,0.7-1.8c0-0.8-0.2-1.4-0.7-1.8\n" +
                "\t\t\t\t\t\ts-1-0.7-1.7-0.7s-1.2,0.2-1.7,0.7c-0.4,0.5-0.7,1.1-0.7,1.8c0,0.8,0.2,1.4,0.7,1.8c0.4,0.5,1,0.7,1.7,0.7\n" +
                "\t\t\t\t\t\tC557.1,331.9,557.7,331.6,558.1,331.2z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M565,333.4c-0.8-0.4-1.4-1-1.8-1.7c-0.4-0.7-0.7-1.5-0.7-2.4s0.2-1.7,0.7-2.4c0.4-0.7,1-1.3,1.8-1.7\n" +
                "\t\t\t\t\t\ts1.7-0.6,2.6-0.6c1,0,1.8,0.2,2.5,0.6s1.2,1,1.6,1.7l-2.1,1.1c-0.5-0.8-1.2-1.3-2.1-1.3c-0.7,0-1.3,0.2-1.7,0.7\n" +
                "\t\t\t\t\t\ts-0.7,1.1-0.7,1.9s0.2,1.4,0.7,1.9s1,0.7,1.7,0.7c0.9,0,1.6-0.4,2.1-1.3l2.1,1.1c-0.3,0.7-0.9,1.3-1.6,1.7\n" +
                "\t\t\t\t\t\tc-0.7,0.4-1.6,0.6-2.5,0.6C566.7,334,565.8,333.8,565,333.4z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M573.4,323.1c-0.3-0.3-0.5-0.6-0.5-1.1c0-0.4,0.2-0.8,0.5-1.1c0.3-0.3,0.7-0.4,1.2-0.4s0.9,0.1,1.2,0.4\n" +
                "\t\t\t\t\t\ts0.5,0.6,0.5,1s-0.2,0.8-0.5,1.1c-0.3,0.3-0.7,0.4-1.2,0.4S573.7,323.3,573.4,323.1z M573.3,324.8h2.7v9.1h-2.7V324.8z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M585.3,325.6c0.8,0.7,1.1,1.7,1.1,3.1v5.2H584v-1.1c-0.5,0.8-1.4,1.3-2.8,1.3c-0.7,0-1.3-0.1-1.8-0.4\n" +
                "\t\t\t\t\t\tc-0.5-0.2-0.9-0.6-1.2-1s-0.4-0.9-0.4-1.4c0-0.9,0.3-1.5,1-2c0.6-0.5,1.6-0.7,3-0.7h2.1c0-0.6-0.2-1-0.5-1.3\n" +
                "\t\t\t\t\t\tc-0.4-0.3-0.9-0.5-1.6-0.5c-0.5,0-1,0.1-1.4,0.2s-0.9,0.4-1.2,0.6l-1-1.9c0.5-0.4,1.1-0.6,1.8-0.8c0.7-0.2,1.4-0.3,2.2-0.3\n" +
                "\t\t\t\t\t\tC583.5,324.6,584.6,325,585.3,325.6z M583.1,331.9c0.4-0.2,0.6-0.5,0.7-0.9v-0.9H582c-1.1,0-1.6,0.4-1.6,1.1\n" +
                "\t\t\t\t\t\tc0,0.3,0.1,0.6,0.4,0.8c0.3,0.2,0.6,0.3,1.1,0.3C582.3,332.3,582.7,332.1,583.1,331.9z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M588.9,321.3h2.7v12.6h-2.7V321.3z\"/>\n" +
                "\t\t\t\t</g>\n" +
                "\t\t\t</g>\n" +
                "                    <g id=\"EmailGroup\">\n" +
                "\t\t\t\t<g>\n" +
                "\t\t\t\t\t<path class=\"stmail4\" d=\"M553.4,263.8v2.2h-9.2v-11.9h9v2.2h-6.3v2.6h5.5v2.1h-5.5v2.8L553.4,263.8L553.4,263.8z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M569.8,257.8c0.7,0.7,1,1.7,1,3v5.2h-2.7v-4.8c0-0.7-0.2-1.3-0.5-1.6c-0.3-0.4-0.7-0.5-1.3-0.5\n" +
                "\t\t\t\t\t\ts-1.1,0.2-1.5,0.6s-0.5,1-0.5,1.8v4.6h-2.7v-4.8c0-1.4-0.6-2.2-1.7-2.2c-0.6,0-1.1,0.2-1.5,0.6s-0.5,1-0.5,1.8v4.6h-2.7V257\n" +
                "\t\t\t\t\t\th2.5v1.1c0.3-0.4,0.8-0.7,1.2-0.9c0.5-0.2,1-0.3,1.6-0.3s1.2,0.1,1.8,0.4s0.9,0.6,1.3,1.1c0.4-0.5,0.8-0.8,1.4-1.1\n" +
                "\t\t\t\t\t\tc0.6-0.3,1.2-0.4,1.9-0.4C568.2,256.7,569.1,257.1,569.8,257.8z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M580.2,257.8c0.8,0.7,1.1,1.7,1.1,3.1v5.2h-2.5V265c-0.5,0.9-1.4,1.3-2.8,1.3c-0.7,0-1.3-0.1-1.8-0.4\n" +
                "\t\t\t\t\t\tc-0.5-0.2-0.9-0.6-1.2-1s-0.4-0.9-0.4-1.4c0-0.9,0.3-1.5,1-2c0.6-0.5,1.6-0.7,3-0.7h2.1c0-0.6-0.2-1-0.5-1.3\n" +
                "\t\t\t\t\t\tc-0.4-0.3-0.9-0.5-1.6-0.5c-0.5,0-1,0.1-1.4,0.2c-0.5,0.2-0.9,0.4-1.2,0.6l-1-1.9c0.5-0.4,1.1-0.6,1.8-0.8\n" +
                "\t\t\t\t\t\tc0.7-0.2,1.4-0.3,2.2-0.3C578.3,256.7,579.4,257.1,580.2,257.8z M577.9,264.1c0.4-0.2,0.6-0.5,0.7-0.9v-0.9h-1.8\n" +
                "\t\t\t\t\t\tc-1.1,0-1.6,0.4-1.6,1.1c0,0.3,0.1,0.6,0.4,0.8s0.6,0.3,1.1,0.3C577.1,264.4,577.5,264.3,577.9,264.1z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M583.8,255.2c-0.3-0.3-0.5-0.6-0.5-1.1s0.2-0.8,0.5-1.1s0.7-0.4,1.2-0.4s0.9,0.1,1.2,0.4\n" +
                "\t\t\t\t\t\tc0.3,0.3,0.5,0.6,0.5,1s-0.2,0.8-0.5,1.1s-0.7,0.4-1.2,0.4S584.1,255.5,583.8,255.2z M583.7,256.9h2.7v9.1h-2.7V256.9z\"/>\n" +
                "                    <path class=\"stmail4\" d=\"M588.8,253.4h2.7V266h-2.7V253.4z\"/>\n" +
                "\t\t\t\t</g>\n" +
                "\t\t\t</g>\n" +
                "\t\t</g>\n" +
                "\t</g>\n" +
                "</g>\n" +
                "</svg>\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "<script>\n" +
                "    const MailBody = document.getElementById('MailBody');\n" +
                "    const NoteF = document.getElementById('NoteF');\n" +
                "    const NoteB = document.getElementById('NoteB');\n" +
                "    const Note = document.getElementById('Note_1_');\n" +
                "    const NoteBase = document.getElementById('Base');\n" +
                "\n" +
                "    openCloseContactInfo();\n" +
                "\n" +
                "    function openCloseContactInfo() {\n" +
                "\n" +
                "        var contactAnimTlm = gsap.timeline({ paused: true, reversed: true });\n" +
                "        contactAnimTlm\n" +
                "            .to(NoteF, { y: -100 })\n" +
                "            .to(NoteB, { y: -100 }, 0)\n" +
                "            .to(NoteF, { opacity: 1, duration: 0 })\n" +
                "            .to(NoteB, { opacity: 0, duration: 0 }, \"+=0\")\n" +
                "            .to(NoteF, { scale: 3, transformOrigin: \"50% 50%\", y: 0 });\n" +
                "\n" +
                "        $(MailBody).click(function () {\n" +
                "            contactAnimTlm.reversed() ? contactAnimTlm.play() : contactAnimTlm.reverse();\n" +
                "        });\n" +
                "    }\n" +
                "</script>";
        helper.setTo(account.getEmail());
        helper.setSubject("QL - KÍCH HOẠT TÀI KHOẢN");
        message.setContent(htmlMsg, "text/html");
//        helper.setText(htmlMsg, true);
        javaMailSender.send(message);
    }

    @Override
    public void sendResetPasswordMail(Account account) throws MessagingException, JsonProcessingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        Map<String, Object> map = Utils.getObjectMapper().readValue(account.getForgotPassword(), Map.class);
        String key = map.get("key").toString();
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String htmlMsg = "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "<!-- 100% body table -->\n" +
                "<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table style=\"background-color: #f2f3f8; max-width:670px; margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "                   align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <tr>\n" +
                "                    <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"text-align:center;\">\n" +
                "                        <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\n" +
                "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\"\n" +
                "                                 alt=\"logo\">\n" +
                "                        </a>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                               style=\"max-width:670px; background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "                            <tr>\n" +
                "                                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td style=\"padding:0 35px;\">\n" +
                "                                    <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">\n" +
                "                                        NHÓM QUẢN TRỊ\n" +
                "                                    </h1>\n" +
                "                                    <p style=\"font-size:15px; color:#455056; margin:10px 0 0; line-height:30px;\">\n" +
                "                                        Bạn hoặc ai đó đã yêu cầu thay đổi mật khẩu vào thời điểm " + dateTimeFormatter.format(new Date()) + "\n" +
                "                                        <br><strong>Sử dụng key 8 số bên dưới để xác nhận thay đổi mật khẩu.<br> Key sẽ hết hạn sau 5 phút</strong>.\n" +
                "                                    </p>\n" +
                "                                    <span\n" +
                "                                            style=\"display:inline-block; vertical-align:middle; margin:10px 0 10px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                                    <p\n" +
                "                                            style=\"color:blue; font-size:18px;line-height:20px; margin:0; font-weight: 500;\">\n" +
                "                                        <strong\n" +
                "                                                style=\"display: block; font-size: 13px; margin: 5px 0 4px 0; font-weight:normal; color:rgba(0,0,0,.64);\">key</strong> <strong style=\" color='blue', font-size:25px \">" + key + "</strong>\n" +
                "                                    </p>\n" +
                "\n" +
                "                                    <a href=\"login.html\"\n" +
                "                                       style=\"background:#20e277;text-decoration:none !important; display:inline-block; font-weight:500; margin-top:24px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Trang\n" +
                "                                        chủ</a>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"text-align:center;\">\n" +
                "                        <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">\n" +
                "                            &copy; <strong>www.sida.com</strong></p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<!--/100% body table-->\n" +
                "</body>";
        helper.setTo(account.getEmail());
        helper.setSubject("QL - KÍCH HOẠT TÀI KHOẢN");
        helper.setText(htmlMsg, true);
        javaMailSender.send(message);

    }
}

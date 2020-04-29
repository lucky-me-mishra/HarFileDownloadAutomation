# HarFileDownloadAutomation
Automation project to download the HAR(HTTP Archive) file for performance optimization
This is a maven based project, so need to create a spring maven testng project. We can call mvn test, mvn install to execute the application.This application is implemented using BrowserMobProxy utility.
Download browser-mob-proxy-2.1.4-bin or latest one and add the ssl security certificates into the browser, inorder to make it work for https(secured) sites.
Steps to install certificate  
  1. go to chrome browser settings
  2. Go to Privcy And Security section
  3. Then do more - And go to manage Certificates and Settings
  4. Go to Intermediate Certification Authorities and import the certificates
 Also if any compilation error due to net.lightbody.bmp.core.har.HAR, then we can add the jar available in the lib folder of browser-mob-proxy-2.1.4-bin folder.

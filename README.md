# WxPGPUtlis
## Getting Started
These instructions will get you a copy of the project up and running on your Intregration Server instance.

### Prerequisites
1. SoftwareAG Integration Server.
2. SoftwareAG Designer.

### Importing the package to IS 
1. Download the zip file [WxPGPUtils.zip].
2. Open your SoftwareAg folder and then go to 'IntegrationServer\instances\default\replicate\inbound'.
3. Paste your zip file there.
![Annotation 2020-05-08 141153](https://user-images.githubusercontent.com/60179170/81388167-82161180-9135-11ea-8e06-27c4f659ee8a.png)
4. Start integration server from your Start menu.
5. Go to your browser then visit http://localhost:5555/
6. Default Credentials
- username: Administrator
- password : manage
7. Click on Packages then 'Install Inbound Releases'.
![jj](https://user-images.githubusercontent.com/60179170/81390581-55fc8f80-9139-11ea-9990-5665249e2cdc.png)
8. Select the zip file and click 'Install Realease'.<br />
![jj](https://user-images.githubusercontent.com/60179170/81391931-8ba27800-913b-11ea-9d26-e158c065bcd2.png)
9.  Shut Down and Restart your Integration Server.
![jj](https://user-images.githubusercontent.com/60179170/81392418-4468b700-913c-11ea-8ba8-9d21e8028140.png)

## Run the java services
1. Start Integration Server.
2. Open your SoftwareAG Designer and connect to the server. To connect right click on 'Default Package' and click on 'Connect to the Server' option.
![jj](https://user-images.githubusercontent.com/60179170/81392609-898ce900-913c-11ea-82fa-126452b990b8.png)
3. After you have successfully connected, double-click on 'Default Package' and select 'WxPGPUtils'. <br/>
![Annotation 2020-05-11 114842](https://user-images.githubusercontent.com/60179170/81529698-00182980-937d-11ea-8787-d748af6881a9.png)
4. Open any one of the four Java Services.
5. Right-click on the code part, goto 'Run as' then click on 'Run Service' and click 'OK'.
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81395848-f9ea3900-9141-11ea-9546-bf8a91a3caa4.png)
6. Provide the input then click on 'OK'. A demo publick key, private key and password has been provided here. 
Note: You can use these for testing or, generate a new key pair by visiting [https://pgpkeygen.com/](https://pgpkeygen.com/).
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81396151-7c72f880-9142-11ea-913f-c49f2397f57a.png)
7. Your output along with input will be displayed as below.
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81396267-b512d200-9142-11ea-8a0b-ba5c572376ad.png)

## Fixing import error
1. If you are facing any import error, right-click on the red mark in fornt of the error line and then double-click 'Fix Project Setup..'
![jj](https://user-images.githubusercontent.com/60179170/81393821-97dc0480-913e-11ea-9086-a546d5bea370.png)
2. Then click on 'here' option. <br/>
![Annotation 2020-05-08 155628](https://user-images.githubusercontent.com/60179170/81396986-15eeda00-9144-11ea-81bf-e1af733cbbe8.png)
3.Then click on Add external JARs.
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81395095-aa573d80-9140-11ea-9d38-b91ab29172d9.png)
4. . Select the two JAR files from 'SoftwareAG_x\IntegrationServer\instances\default\packages\WxPGPUtils\code\jars\static'. 
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81394879-4df41e00-9140-11ea-91b7-c035a72f8104.png)
5. And then click on 'Apply and Close'. This will fix the error.
![Annotation 2020-05-08 151901](https://user-images.githubusercontent.com/60179170/81394495-a0810a80-913f-11ea-8432-fca2bd05e724.png)

## About the services
<b>1. Encrypt </b><br/>
<i><b>Input:</b></i><br/>
 Public Key, Datatype: String<br/>
 Input String, Datatype: String <br/>
<i><b>Output:</b></i><br/>
 Encrypted String, Datatype: String. <br/> <br/>
![Annotation 2020-05-11 114842](https://user-images.githubusercontent.com/60179170/81530662-eed01c80-937e-11ea-816b-24fa5581cc80.png)<br/> <br/>
<b>2. Decrypt </b><br/>
<i><b>Input:</b></i><br/>
 Private Key, Datatype: String<br/>
 Encrypted String, Datatype: String<br/>
 Password, Datatype: String<br/>
<i><b>Output:</b></i><br/> 
 Decrypted String, Datatype: String. <br/><br/>
![Annotation 2020-05-11 114842](https://user-images.githubusercontent.com/60179170/81530883-5dad7580-937f-11ea-9da9-65a7b963c0c0.png)<br/> <br/>
<b>3. Sign </b><br/>
<i><b>Input:</b></i><br/>
 Private Key, Datatype: String.<br/>
 Message, Datatype: String. <br/>
 Password, Datatype: String. <br/>
<i><b>Output:</b></i><br/>
 Signed Message, Datatype: Byte[]. <br/> <br/>
![Annotation 2020-05-11 114842](https://user-images.githubusercontent.com/60179170/81531497-75392e00-9380-11ea-9eae-8984d2c1e6b8.png)
## Contributors
[Anshuman Saikia](https://github.com/anshu96788)
[Dipankar Dutta](https://github.com/DipankarDDUT) 
[Nawajish Laskar](https://github.com/Nawajish)

## License
This project is licensed under the Apache 2.0 License - see the [LICENSE.md](https://github.com/SoftwareAG/webmethods-microservicesruntime-samples/blob/master/LICENSE)file for details

______________________
These tools are provided as-is and without warranty or support. They do not constitute part of the Software AG product suite. Users are free to use, fork and modify them, subject to the license agreement. While Software AG welcomes contributions, we cannot guarantee to include every contribution in the master project.

Contact us at [TECHcommunity](mailto:technologycommunity@softwareag.com?subject=Github/SoftwareAG) if you have any questions.

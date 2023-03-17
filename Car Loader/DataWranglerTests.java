import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.*;

public class DataWranglerTests {

    protected ICarLoader loader;
    protected ArrayList<ICar> cars;
    protected Car testCar;
    protected Car testCar2;
    @BeforeEach
    public void initTest() throws FileNotFoundException {
        loader=new CarLoader();
        cars=loader.loadCars("cars1.xml");
        testCar=new Car("123ABC", "2022", "Honda", "CR-V", "12345");
        testCar2=new Car("123ABD", "2022", "Honda", "CR-V", "12345");
    }

    /**
     * Tests the frontend developers command loop, and the search by make with an empty input.
     */
    @Test
    public void CodeReviewOfFrontendDeveloperTest1() throws FileNotFoundException {
        TextUITester tester = new TextUITester("2\n129FFE\n6\n");
        IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
        CarLoader loader = new CarLoader();
        ArrayList<ICar> cars = loader.loadCars("cars1.xml");

        BackendInterface backend = new Backend(rbt);

        for (ICar car: cars) {
            backend.addCar(car);
        }

        LicensePlateCheckerInterface checker = new AELicensePlateChecker();

        Scanner scanner = new Scanner(System.in);

        FrontendInterface frontend = new Frontend(scanner, backend, checker);

        frontend.runCommandLoop();
        String output = tester.checkOutput();
//        System.out.println(output);
        Assertions.assertEquals(output, "You are in the Main Menu:\n" +
                "\t\t1) General Search\n" +
                "\t\t2) Search By Plate\n" +
                "\t\t3) Set Minimum Price\n" +
                "\t\t4) Set Maximum Price\n" +
                "\t\t5) Buy a Car\n" +
                "\t\t6) Exit Application\n" +
                "You are in the Search by Plate Menu:\n" +
                "\t\tEnter a license plate to search for:\n" +
                "Matches\n" +
                "1. 2008 Mitsubishi Lancer Evolution X GSR \"Welcome Pack\", Plate: 129FFE, Price: $20663\n" +
                "You are in the Main Menu:\n" +
                "\t\t1) General Search\n" +
                "\t\t2) Search By Plate\n" +
                "\t\t3) Set Minimum Price\n" +
                "\t\t4) Set Maximum Price\n" +
                "\t\t5) Buy a Car\n" +
                "\t\t6) Exit Application\n" +
                "Goodbye!\n");
    }
    /**
     * Tests the frontend developers command loop, and the search by license plate.
     */
    @Test
    public void CodeReviewOfFrontendDeveloperTest2() throws FileNotFoundException{
        TextUITester tester = new TextUITester("1\n\n6\n");
        IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
        CarLoader loader = new CarLoader();
        ArrayList<ICar> cars = loader.loadCars("cars1.xml");

        BackendInterface backend = new Backend(rbt);

        for (ICar car: cars) {
            backend.addCar(car);
        }

        LicensePlateCheckerInterface checker = new AELicensePlateChecker();

        Scanner scanner = new Scanner(System.in);

        FrontendInterface frontend = new Frontend(scanner, backend, checker);

        frontend.runCommandLoop();
        String output = tester.checkOutput();
//        System.out.println(output);
        Assertions.assertEquals(output, "You are in the Main Menu:\n" +
                "\t\t1) General Search\n" +
                "\t\t2) Search By Plate\n" +
                "\t\t3) Set Minimum Price\n" +
                "\t\t4) Set Maximum Price\n" +
                "\t\t5) Buy a Car\n" +
                "\t\t6) Exit Application\n" +
                "You are in the General Search Menu:\n" +
                "\t\tEnter a make to look up (leave empty for all makes): \n" +
                "Matches (No make, no min price, no max price)\n" +
                "1. 1971 Plymouth Cuda 426 HEMI, Plate: 003YNK, Price: $13406\n" +
                "2. 1986 Ford Escort RS Turbo, Plate: 006IKV, Price: $34495\n" +
                "3. 2018 Audi RS 4 Avant, Plate: 008CCB, Price: $9922\n" +
                "4. 2013 Mercedes-Benz G 65 AMG, Plate: 010EVW, Price: $36842\n" +
                "5. 2015 Jaguar F-TYPE R CoupÃ©, Plate: 012WJX, Price: $15517\n" +
                "6. 1968 Plymouth Barracuda Formula-S, Plate: 012ZBA, Price: $40835\n" +
                "7. 2002 Koenigsegg CC8S, Plate: 013HIX, Price: $25385\n" +
                "8. 2015 Jaguar XKR-S GT, Plate: 013HWO, Price: $24980\n" +
                "9. 1984 Peugeot 205 T16, Plate: 013PUF, Price: $35500\n" +
                "10. 2015 Infiniti Q60 Concept, Plate: 014MPM, Price: $31729\n" +
                "11. 2019 Ford #2069 Ford Bronco R \"Welcome Pack\", Plate: 016VZM, Price: $25833\n" +
                "12. 1987 Buick Regal GNX, Plate: 018ZSP, Price: $3698\n" +
                "13. 1997 Nissan Skyline GT-R V-Spec, Plate: 030FOX, Price: $14172\n" +
                "14. 1995 Ferrari F50, Plate: 038ESX, Price: $15253\n" +
                "15. 2005 Ford Mustang, Plate: 042KIY, Price: $27132\n" +
                "16. 2007 Honda Civic Type R, Plate: 048SWB, Price: $27629\n" +
                "17. 1972 Hoonigan Chevrolet Napalm Nova, Plate: 051TSL, Price: $6652\n" +
                "18. 2017 VUHL 05RR, Plate: 053NHK, Price: $34256\n" +
                "19. 1962 Ferrari 250 GT Berlinetta Lusso, Plate: 057RTQ, Price: $3483\n" +
                "20. 1995 Porsche 911 Carrera 2 by Gunther Werks, Plate: 063UTI, Price: $32016\n" +
                "21. 1967 Chevrolet Corvette Stingray 427, Plate: 068ZHV, Price: $37538\n" +
                "22. 2017 Maserati Levante S, Plate: 069GRC, Price: $8862\n" +
                "23. 1979 Toyota FJ40, Plate: 070SNZ, Price: $28797\n" +
                "24. 2012 Hot Wheels Bad To The Blade, Plate: 074SVD, Price: $16152\n" +
                "25. 2011 Penhall The Cholla, Plate: 077EYY, Price: $39845\n" +
                "26. 1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 081AMW, Price: $13043\n" +
                "27. 1977 Holden Torana A9X, Plate: 086TRN, Price: $20121\n" +
                "28. 1990 Porsche 911 reimagined by Singer - DLS, Plate: 093AIZ, Price: $16451\n" +
                "29. 1969 Chevrolet Nova Super Sport 396, Plate: 093QDH, Price: $40402\n" +
                "30. 2012 Nissan GT-R Black Edition (R35), Plate: 093URC, Price: $23394\n" +
                "31. 2012 Nissan GT-R Black Edition (R35), Plate: 093YHS, Price: $20138\n" +
                "32. 1992 Ferrari 512 TR, Plate: 098PTJ, Price: $36513\n" +
                "33. 2012 Lamborghini Aventador LP700-4, Plate: 098RXY, Price: $40950\n" +
                "34. 1966 Jaguar XJ13, Plate: 103HRR, Price: $33962\n" +
                "35. 1986 Hoonigan Ford RS200 Evolution, Plate: 107URS, Price: $8435\n" +
                "36. 2013 Mercedes-Benz E 63 AMG, Plate: 111YTU, Price: $21397\n" +
                "37. 1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 114ISA, Price: $1556\n" +
                "38. 1973 Ford Escort RS1600, Plate: 123IXW, Price: $32758\n" +
                "39. 2008 Mitsubishi Lancer Evolution X GSR \"Welcome Pack\", Plate: 129FFE, Price: $20663\n" +
                "40. 2000 Nissan Silvia Spec-R, Plate: 131ZFH, Price: $23558\n" +
                "41. 1994 Honda Prelude Si, Plate: 132RUV, Price: $36999\n" +
                "42. 2011 BMW X5 M, Plate: 140WCX, Price: $40928\n" +
                "43. 2017 Ford F-150 Raptor, Plate: 145LTJ, Price: $12632\n" +
                "44. 1986 Ford Mustang SVO, Plate: 147TUX, Price: $6474\n" +
                "45. 1994 Honda Prelude Si, Plate: 149IIF, Price: $14606\n" +
                "46. 1997 McLaren F1 GT, Plate: 150OJU, Price: $12423\n" +
                "47. 2012 BMW M5, Plate: 151MQD, Price: $1023\n" +
                "48. 2010 Nissan 370Z, Plate: 163ZGY, Price: $24266\n" +
                "49. 1960 Chevrolet Corvette, Plate: 180FRI, Price: $6591\n" +
                "50. 2000 Hot Wheels Deora II, Plate: 183VCC, Price: $40552\n" +
                "51. 2017 Ford GT, Plate: 184AFH, Price: $1935\n" +
                "52. 1973 Land Rover Range Rover, Plate: 184DOV, Price: $1671\n" +
                "53. 1965 Ford F-100, Plate: 187QSX, Price: $38156\n" +
                "54. 2019 Ford Ranger Raptor, Plate: 188QJX, Price: $6926\n" +
                "55. 1977 Ford Escort RS1800, Plate: 190QMR, Price: $14228\n" +
                "56. 1969 Dodge Charger Daytona HEMI, Plate: 191FIL, Price: $37205\n" +
                "57. 2005 Ferrari FXX, Plate: 196IWQ, Price: $39738\n" +
                "58. 2012 Mercedes-Benz SLK 55 AMG, Plate: 206RZV, Price: $22629\n" +
                "59. 2004 Mitsubishi Lancer Evolution VIII MR, Plate: 212QLQ, Price: $12591\n" +
                "60. 1992 Bugatti EB110 Super Sport, Plate: 214LZA, Price: $11977\n" +
                "61. 2013 KTM X-Bow R, Plate: 218BTB, Price: $3110\n" +
                "62. 1967 Lamborghini Miura P400, Plate: 219QRB, Price: $21214\n" +
                "63. 1979 Toyota FJ40, Plate: 221FYW, Price: $28431\n" +
                "64. 2003 Toyota Celica SS-I, Plate: 221NLA, Price: $11382\n" +
                "65. 2016 Jeep Trailcat, Plate: 221PIZ, Price: $1877\n" +
                "66. 1996 Ferrari F50 GT, Plate: 226GUS, Price: $27392\n" +
                "67. 2015 Dodge Challenger SRT Hellcat, Plate: 226YHG, Price: $28746\n" +
                "68. 1973 AMC Gremlin X, Plate: 233RQS, Price: $13935\n" +
                "69. 2014 Porsche 918 Spyder, Plate: 240JHR, Price: $27374\n" +
                "70. 2021 McLaren 765LT Coupe, Plate: 241OEF, Price: $26475\n" +
                "71. 2012 Ferrari 599XX Evolution, Plate: 249ROG, Price: $15958\n" +
                "72. 2014 BMW M4 CoupÃ©, Plate: 250RMO, Price: $12920\n" +
                "73. 1987 Buick Regal GNX, Plate: 252MXW, Price: $39415\n" +
                "74. 2015 Lexus RC F, Plate: 254WOA, Price: $16289\n" +
                "75. 2015 Chevrolet Corvette Z06, Plate: 264TAQ, Price: $25351\n" +
                "76. 1995 Porsche 911 GT2, Plate: 264XIQ, Price: $7767\n" +
                "77. 1959 Ford Anglia 105E, Plate: 266COW, Price: $22618\n" +
                "78. 2019 Lamborghini Urus, Plate: 266IPG, Price: $11276\n" +
                "79. 1964 Aston Martin DB5, Plate: 270LYX, Price: $20250\n" +
                "80. 2020 Formula Drift #151 Toyota GR Supra, Plate: 277PPU, Price: $28826\n" +
                "81. 2015 Porsche Cayman GTS, Plate: 281JRZ, Price: $3159\n" +
                "82. 2005 Ford GT, Plate: 282OHA, Price: $28375\n" +
                "83. 2015 Ferrari 488 GTB, Plate: 288OFX, Price: $39787\n" +
                "84. 1970 International Scout 800A, Plate: 291AEU, Price: $30662\n" +
                "85. 2012 Hot Wheels Bad To The Blade, Plate: 291IYQ, Price: $32870\n" +
                "86. 1986 Ford Mustang SVO, Plate: 295PUK, Price: $40535\n" +
                "87. 2009 Audi RS 6, Plate: 296HAS, Price: $14274\n" +
                "88. 2005 Ford GT, Plate: 298TJF, Price: $1574\n" +
                "89. 1986 MG Metro 6R4, Plate: 298VSI, Price: $10757\n" +
                "90. 2018 Porsche Macan Rally Raid, Plate: 299QJH, Price: $19754\n" +
                "91. 1959 Ford Anglia 105E, Plate: 302DYG, Price: $18159\n" +
                "92. 2010 Porsche 911 Sport Classic, Plate: 305HUF, Price: $27900\n" +
                "93. 1982 DeLorean DMC-12, Plate: 305TTX, Price: $6850\n" +
                "94. 2018 Hot Wheels 2 Jet Z, Plate: 314XLM, Price: $20159\n" +
                "95. 2018 Honda Civic Type R, Plate: 319OJP, Price: $26397\n" +
                "96. 2018 Exomotive Exocet Off-Road Forza Edition, Plate: 320HXZ, Price: $39276\n" +
                "97. 2014 Jeep Grand Cherokee SRT, Plate: 323RTW, Price: $38824\n" +
                "98. 1988 Lamborghini Countach LP5000 QV, Plate: 327PLB, Price: $25149\n" +
                "99. 2014 Ford Fiesta ST, Plate: 332VZF, Price: $5255\n" +
                "100. 2017 Nissan GT-R (R35), Plate: 333CRH, Price: $21958\n" +
                "101. 1971 Lotus Elan Sprint, Plate: 339BZK, Price: $18894\n" +
                "102. 2008 Mitsubishi Lancer Evolution X GSR \"Welcome Pack\", Plate: 341VBU, Price: $31505\n" +
                "103. 2014 Mini X-Raid All4 Racing Countryman, Plate: 343RAC, Price: $9791\n" +
                "104. 2020 Sierra Cars #23 Yokohama Alpha, Plate: 350DEJ, Price: $38910\n" +
                "105. 2011 Mercedes-Benz SLS AMG, Plate: 350NJX, Price: $40132\n" +
                "106. 2018 Mercedes-Benz X-Class, Plate: 351NQU, Price: $40146\n" +
                "107. 2019 BMW Z4, Plate: 352AMF, Price: $4505\n" +
                "108. 2004 Honda Civic Type R, Plate: 352CYZ, Price: $19612\n" +
                "109. 1963 Volkswagen Beetle, Plate: 352PLB, Price: $15798\n" +
                "110. 1995 Formula Drift #34 Toyota Supra MkIV, Plate: 353LGJ, Price: $18135\n" +
                "111. 1945 Willys MB Jeep, Plate: 358SLH, Price: $39927\n" +
                "112. 2015 McLaren 570S CoupÃ©, Plate: 363LMZ, Price: $15899\n" +
                "113. 2007 Honda Civic Type R, Plate: 370PBN, Price: $21592\n" +
                "114. 2019 Brabham BT62, Plate: 371CHH, Price: $39251\n" +
                "115. 1992 Toyota Celica GT-Four RC ST185, Plate: 382GDH, Price: $8288\n" +
                "116. 1997 Mitsubishi GTO, Plate: 382UWY, Price: $40141\n" +
                "117. 1983 Audi Sport Quattro, Plate: 390PJB, Price: $39526\n" +
                "118. 2003 Volkswagen Golf R32, Plate: 390TEN, Price: $27516\n" +
                "119. 2013 KTM X-Bow R, Plate: 396GSS, Price: $28973\n" +
                "120. 1979 Toyota FJ40, Plate: 399JPE, Price: $23788\n" +
                "121. 2019 Hyundai Veloster N, Plate: 400HVK, Price: $33998\n" +
                "122. 2007 Formula Drift #117 599 GTB Fiorano, Plate: 401YQS, Price: $32963\n" +
                "123. 1994 Mazda MX-5 Miata, Plate: 403IHE, Price: $39937\n" +
                "124. 1996 Ferrari F50 GT, Plate: 407YZY, Price: $27151\n" +
                "125. 1998 Nissan Silvia K's AERO, Plate: 413PCY, Price: $38628\n" +
                "126. 1994 Ferrari F355 Berlinetta, Plate: 414FJF, Price: $7215\n" +
                "127. 2010 Noble M600, Plate: 415LTM, Price: $34032\n" +
                "128. 1954 Mercedes-Benz 300 SL CoupÃ©, Plate: 415TSO, Price: $3395\n" +
                "129. 2020 Formula Drift #151 Toyota GR Supra, Plate: 416PKD, Price: $29086\n" +
                "130. 1969 Lola #6 Penske Sunoco T70 MkIIIB, Plate: 418CML, Price: $26726\n" +
                "131. 2017 Ford GT, Plate: 420CPV, Price: $14412\n" +
                "132. 1985 Ford RS200 Evolution, Plate: 426ATW, Price: $9605\n" +
                "133. 2015 McLaren 650S CoupÃ©, Plate: 429GGM, Price: $33164\n" +
                "134. 1971 Porsche #23 917/20, Plate: 440EWM, Price: $8431\n" +
                "135. 1965 Pontiac GTO, Plate: 441EMA, Price: $33360\n" +
                "136. 2018 McLaren 720S CoupÃ©, Plate: 446XIV, Price: $37715\n" +
                "137. 1997 Lamborghini Diablo SV, Plate: 448RCJ, Price: $3873\n" +
                "138. 2010 Jaguar C-X75, Plate: 451AXI, Price: $15654\n" +
                "139. 2003 Honda S2000, Plate: 452XFU, Price: $16393\n" +
                "140. 2007 Ferrari 430 Scuderia, Plate: 456IXB, Price: $25914\n" +
                "141. 2022 Extreme-E #22 JBXE, Plate: 457VUK, Price: $3605\n" +
                "142. 1986 Ford Mustang SVO, Plate: 460DWM, Price: $15491\n" +
                "143. 1974 Honda Civic RS, Plate: 464WYH, Price: $37927\n" +
                "144. 2003 BMW M5, Plate: 469PPQ, Price: $5654\n" +
                "145. 2010 Maserati Gran Turismo S Forza Edition, Plate: 472LBO, Price: $7082\n" +
                "146. 2015 Land Rover Range Rover Sport SVR, Plate: 481CPR, Price: $32553\n" +
                "147. 2015 Formula Drift #13 Ford Mustang, Plate: 482JUH, Price: $8875\n" +
                "148. 1998 Volkswagen GTI VR6 Mk3, Plate: 482YPK, Price: $3302\n" +
                "149. 2018 Honda Civic Type R, Plate: 489KGU, Price: $28891\n" +
                "150. 2007 Honda Civic Type R, Plate: 492EBP, Price: $27550\n" +
                "151. 2017 Bentley Continental Supersports, Plate: 494DJK, Price: $13510\n" +
                "152. 1968 Plymouth Barracuda Formula-S, Plate: 497COI, Price: $21628\n" +
                "153. 1999 Ford Racing Puma, Plate: 497SEX, Price: $2395\n" +
                "154. 2015 Infiniti Q60 Concept, Plate: 498HEU, Price: $27394\n" +
                "155. 1960 Chevrolet Corvette, Plate: 500FRU, Price: $27732\n" +
                "156. 1977 Ford #5 Escort RS1800 MKII, Plate: 500WPZ, Price: $15146\n" +
                "157. 1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 501UZR, Price: $37760\n" +
                "158. 1971 Meyers Manx Forza Edition, Plate: 508EAE, Price: $30304\n" +
                "159. 2010 Maserati Gran Turismo S Forza Edition, Plate: 510GXL, Price: $38203\n" +
                "160. 1969 Dodge Charger R/T, Plate: 516IGH, Price: $18982\n" +
                "161. 2012 Mercedes-Benz SLK 55 AMG, Plate: 519UQQ, Price: $9479\n" +
                "162. 2021 Sierra Cars RX3, Plate: 523ICP, Price: $33315\n" +
                "163. 1963 Volkswagen Beetle, Plate: 523VOZ, Price: $4351\n" +
                "164. 1945 Willys MB Jeep, Plate: 526VMS, Price: $25113\n" +
                "165. 1957 Chevrolet Bel Air, Plate: 526WCT, Price: $38581\n" +
                "166. 1958 Morris Minor 1000 Forza Edition, Plate: 533INQ, Price: $25236\n" +
                "167. 1964 Ford GT40 Mk I, Plate: 534RNL, Price: $33820\n" +
                "168. 2015 MG MG3, Plate: 544BTV, Price: $9875\n" +
                "169. 1992 Hoonigan Mazda RX-7 Twerkstallion, Plate: 545BDB, Price: $33666\n" +
                "170. 2005 TVR Sagaris, Plate: 546RID, Price: $34600\n" +
                "171. 2016 Cadillac CTS-V Sedan, Plate: 551KUI, Price: $34228\n" +
                "172. 2018 Jaguar I-Pace, Plate: 554FON, Price: $32428\n" +
                "173. 2022 Extreme-E #22 JBXE, Plate: 556RBI, Price: $16890\n" +
                "174. 2022 Extreme-E #58 McLaren Racing, Plate: 566FQJ, Price: $6063\n" +
                "175. 2018 McLaren 600LT CoupÃ©, Plate: 566WLS, Price: $8948\n" +
                "176. 1997 Volvo 850 R, Plate: 568QSX, Price: $5718\n" +
                "177. 2011 BMW X5 M, Plate: 568SJD, Price: $23885\n" +
                "178. 2014 Local Motors Rally Fighter, Plate: 568WPQ, Price: $15371\n" +
                "179. 2019 Nissan 370Z Nismo, Plate: 571JIP, Price: $23088\n" +
                "180. 2020 Chevrolet Corvette Stingray CoupÃ©, Plate: 577GIE, Price: $34745\n" +
                "181. 2013 Aston Martin V12 Vantage S, Plate: 584ECP, Price: $20261\n" +
                "182. 1995 Ferrari F50, Plate: 584FRH, Price: $38594\n" +
                "183. 2002 Ferrari Enzo Ferrari, Plate: 586SBT, Price: $5138\n" +
                "184. 1983 GMC Vandura G-1500, Plate: 594BKR, Price: $21228\n" +
                "185. 2016 Nissan TITAN Warrior Concept, Plate: 598GVK, Price: $22304\n" +
                "186. 2018 Renault Megane R.S., Plate: 600KJL, Price: $24398\n" +
                "187. 2018 Ford Mustang RTR Spec 5, Plate: 601OJE, Price: $6781\n" +
                "188. 2001 Audi RS 4 Avant, Plate: 612WFG, Price: $34894\n" +
                "189. 2022 Extreme-E #125 ABT Cupra XE, Plate: 623DLJ, Price: $19540\n" +
                "190. 2014 Ford #11 Rockstar F-150 Trophy Truck, Plate: 625KJU, Price: $5151\n" +
                "191. 2018 Bugatti Chiron, Plate: 627ISW, Price: $18622\n" +
                "192. 2017 Aston Martin DB11, Plate: 633CSS, Price: $33179\n" +
                "193. 2021 Aston Martin DBX, Plate: 634QKG, Price: $2289\n" +
                "194. 2011 Volkswagen Scirocco R, Plate: 636PHV, Price: $5380\n" +
                "195. 2020 Toyota Tundra TRD Pro, Plate: 640GOW, Price: $21767\n" +
                "196. 1969 Ferrari Dino 246 GT, Plate: 641JYH, Price: $16007\n" +
                "197. 1970 Chevrolet Chevelle Super Sport 454, Plate: 643JFB, Price: $28237\n" +
                "198. 2005 Mazda Mazdaspeed MX-5, Plate: 644FZX, Price: $37106\n" +
                "199. 1969 Hot Wheels Twin Mill, Plate: 648JDI, Price: $1843\n" +
                "200. 1997 Mitsubishi GTO, Plate: 658BXL, Price: $38728\n" +
                "201. 1993 Ford SVT Cobra R, Plate: 663AHJ, Price: $23748\n" +
                "202. 2006 Hummer H1 Alpha, Plate: 667FWN, Price: $14189\n" +
                "203. 1968 Dodge Dart HEMI Super Stock, Plate: 669HGO, Price: $35328\n" +
                "204. 2018 Honda Civic Type R, Plate: 672LWW, Price: $26548\n" +
                "205. 1986 MG Metro 6R4, Plate: 673DBJ, Price: $17170\n" +
                "206. 2011 Lamborghini Sesto Elemento, Plate: 674VDG, Price: $12938\n" +
                "207. 1994 Hoonigan Ford Escort Cosworth WRC \"Cossie V2\", Plate: 675LEF, Price: $40667\n" +
                "208. 2018 Ferrari FXX-K Evo, Plate: 679QCU, Price: $35780\n" +
                "209. 1969 Hot Wheels Twin Mill, Plate: 701LCZ, Price: $23541\n" +
                "210. 2017 Ford F-150 Raptor, Plate: 703CAK, Price: $5119\n" +
                "211. 2018 Formula Drift #64 Nissan 370Z, Plate: 707OXI, Price: $39201\n" +
                "212. 1969 Lola #6 Penske Sunoco T70 MkIIIB, Plate: 708DRX, Price: $10343\n" +
                "213. 1969 Hot Wheels Twin Mill, Plate: 714HUM, Price: $27666\n" +
                "214. 2019 Porsche 911 GT3 RS, Plate: 715NNG, Price: $3498\n" +
                "215. 2001 Audi RS 4 Avant, Plate: 716DJR, Price: $10462\n" +
                "216. 2012 Jeep Wrangler Rubicon, Plate: 719EMA, Price: $12336\n" +
                "217. 2013 Ford Shelby GT500, Plate: 719VAR, Price: $8781\n" +
                "218. 1992 Hoonigan Mazda RX-7 Twerkstallion, Plate: 722BQG, Price: $11440\n" +
                "219. 2016 Porsche Cayman GT4, Plate: 723WLP, Price: $30846\n" +
                "220. 2016 Aston Martin Vantage GT12, Plate: 727NXJ, Price: $29634\n" +
                "221. 1982 DeLorean DMC-12, Plate: 738FPP, Price: $20706\n" +
                "222. 1969 Volkswagen Class 5/1600 Baja Bug, Plate: 748EUS, Price: $25104\n" +
                "223. 2020 Toyota GR Supra, Plate: 748ZYT, Price: $28343\n" +
                "224. 1958 Austin-Healey Sprite MkI, Plate: 749DWG, Price: $3760\n" +
                "225. 1988 Lamborghini Countach LP5000 QV, Plate: 758JCM, Price: $14924\n" +
                "226. 2017 Chevrolet Camaro ZL1, Plate: 758SFQ, Price: $25507\n" +
                "227. 1930 Bentley 8 Litre, Plate: 769CQV, Price: $26683\n" +
                "228. 2005 Honda NSX-R GT, Plate: 769IMV, Price: $11615\n" +
                "229. 1965 Mini Cooper S Forza Edition, Plate: 776QHU, Price: $6540\n" +
                "230. 2013 Mercedes-Benz E 63 AMG, Plate: 779RUT, Price: $40174\n" +
                "231. 2008 Renault MÃ©gane R26.R, Plate: 780KAS, Price: $16599\n" +
                "232. 2016 Vauxhall Corsa VXR, Plate: 781WCO, Price: $5807\n" +
                "233. 2008 Dodge Magnum SRT-8, Plate: 782EHU, Price: $40586\n" +
                "234. 1997 Lotus Elise GT1, Plate: 783MOU, Price: $15206\n" +
                "235. 1973 Holden HQ Monaro GTS 350, Plate: 785RIM, Price: $2433\n" +
                "236. 1998 Mercedes-Benz AMG CLK GTR Forza Edition, Plate: 788AAO, Price: $35495\n" +
                "237. 2016 Audi R8 V10 plus, Plate: 797ZVY, Price: $24240\n" +
                "238. 2013 Subaru BRZ, Plate: 801MAM, Price: $37776\n" +
                "239. 1984 Opel Manta 400, Plate: 811AGJ, Price: $17331\n" +
                "240. 2015 Formula Drift #13 Ford Mustang, Plate: 820WQX, Price: $3318\n" +
                "241. 1971 Porsche #23 917/20, Plate: 826WSM, Price: $24104\n" +
                "242. 2015 Chevrolet Camaro Z/28, Plate: 827UKZ, Price: $39169\n" +
                "243. 1975 Forsberg Racing Nissan \"Gold Legend\" Datsun 280Z, Plate: 830QPA, Price: $14617\n" +
                "244. 1965 Ford Mustang GT Coupe, Plate: 834CXT, Price: $34557\n" +
                "245. 1997 Volvo 850 R, Plate: 835NAI, Price: $2593\n" +
                "246. 2002 Mazda RX-7 Spirit R Type-A, Plate: 841MTN, Price: $16112\n" +
                "247. 1977 Pontiac Firebird Trans Am, Plate: 842WAM, Price: $24876\n" +
                "248. 2018 ATS GT, Plate: 843MSB, Price: $8306\n" +
                "249. 1987 Nissan Skyline GTS-R (HR31), Plate: 845OVL, Price: $34543\n" +
                "250. 1969 Ferrari Dino 246 GT, Plate: 847EXI, Price: $36454\n" +
                "251. 1957 Chevrolet Bel Air, Plate: 849MLP, Price: $40724\n" +
                "252. 2011 Lamborghini Sesto Elemento, Plate: 850DIT, Price: $10785\n" +
                "253. 1969 Nissan Fairlady Z 432, Plate: 851YQJ, Price: $14073\n" +
                "254. 2019 Ford Ranger Raptor, Plate: 852PKN, Price: $10057\n" +
                "255. 2019 Porsche 911 Speedster, Plate: 859GQV, Price: $15787\n" +
                "256. 2014 BMW M4 CoupÃ©, Plate: 860EYL, Price: $18129\n" +
                "257. 2017 Ford #14 Rahal Letterman Lanigan Racing GRC Fiesta, Plate: 871JBO, Price: $23519\n" +
                "258. 2015 Alumi Craft Class 10 Race Car, Plate: 873NWJ, Price: $1954\n" +
                "259. 1998 Mercedes-Benz AMG CLK GTR, Plate: 876BHJ, Price: $25144\n" +
                "260. 1970 GMC Jimmy, Plate: 877AFO, Price: $16166\n" +
                "261. 2018 Chevrolet Hot Wheels COPO Camaro, Plate: 880HFT, Price: $2606\n" +
                "262. 1969 Chevrolet Camaro Super Sport Coupe, Plate: 880OVV, Price: $10015\n" +
                "263. 2014 Porsche 918 Spyder, Plate: 881KVC, Price: $2692\n" +
                "264. 2015 Mercedes-Benz #24 Tankpool24 Racing Truck Forza Edition, Plate: 882CLP, Price: $32238\n" +
                "265. 2014 Local Motors Rally Fighter, Plate: 883EGW, Price: $36957\n" +
                "266. 2019 Toyota Tacoma TRD Pro, Plate: 885LLB, Price: $28336\n" +
                "267. 1992 Nissan Silvia CLUB K's, Plate: 885OIS, Price: $28453\n" +
                "268. 2008 Dodge Magnum SRT-8, Plate: 886XMC, Price: $28565\n" +
                "269. 2010 Maserati Gran Turismo S Forza Edition, Plate: 890FAW, Price: $39894\n" +
                "270. 1996 Chevrolet Impala Super Sport, Plate: 892PGR, Price: $12015\n" +
                "271. 2017 Mercedes-AMG GT R, Plate: 894RDK, Price: $39214\n" +
                "272. 2003 Nissan Fairlady Z Forza Edition, Plate: 900ITQ, Price: $3494\n" +
                "273. 1999 Dodge Viper GTS ACR, Plate: 901FUI, Price: $21839\n" +
                "274. 2019 Rimac Concept Two, Plate: 901NCQ, Price: $27994\n" +
                "275. 2015 BMW X6 M, Plate: 907DHN, Price: $40255\n" +
                "276. 2003 Ferrari 360 Challenge Stradale, Plate: 916UQE, Price: $37535\n" +
                "277. 2018 DeBerti Chevrolet Silverado 1500 Drift Truck, Plate: 917XJD, Price: $40405\n" +
                "278. 2018 Audi TT RS, Plate: 923CRR, Price: $38445\n" +
                "279. 2016 Porsche 911 GT3 RS, Plate: 927CGE, Price: $3939\n" +
                "280. 1977 Ford #5 Escort RS1800 MKII, Plate: 934LRK, Price: $32708\n" +
                "281. 2020 BMW M8 Competition Coupe, Plate: 936ADR, Price: $26018\n" +
                "282. 2018 Porsche Macan Rally Raid, Plate: 939NKB, Price: $5026\n" +
                "283. 2011 Lamborghini Sesto Elemento, Plate: 939TFZ, Price: $33349\n" +
                "284. 2002 Nissan Skyline GT-R V-Spec II, Plate: 945RDY, Price: $4219\n" +
                "285. 2019 DeBerti Toyota Tacoma TRD \"The Performance Truck\", Plate: 956BAG, Price: $31648\n" +
                "286. 1970 Chevrolet Chevelle Super Sport 454, Plate: 962ZGD, Price: $23865\n" +
                "287. 1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 965MOG, Price: $19079\n" +
                "288. 2005 Ferrari FXX, Plate: 965SIV, Price: $19525\n" +
                "289. 2022 Extreme-E #23 Genesys Andretti United, Plate: 968CGR, Price: $15746\n" +
                "290. 2002 Nissan Skyline GT-R V-Spec II, Plate: 969NQE, Price: $34956\n" +
                "291. 2019 McLaren 720S Spider, Plate: 974EAF, Price: $14112\n" +
                "292. 2019 Brabham BT62, Plate: 979LRM, Price: $4114\n" +
                "293. 2022 Extreme-E #44 X44, Plate: 979WFA, Price: $37979\n" +
                "294. 2019 Aston Martin DBS Superleggera, Plate: 980IEG, Price: $16302\n" +
                "295. 2019 McLaren Speedtail, Plate: 987IOW, Price: $29958\n" +
                "296. 2019 Porsche 911 Speedster, Plate: 987UDY, Price: $37205\n" +
                "297. 2014 McLaren 650S Spider, Plate: 991AVK, Price: $2548\n" +
                "298. 2020 Lexus RC F Track Edition, Plate: 995HMW, Price: $11737\n" +
                "299. 1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 996GAY, Price: $19822\n" +
                "300. 2009 Ferrari 458 Italia, Plate: 997VZJ, Price: $18403\n" +
                "You are in the Main Menu:\n" +
                "\t\t1) General Search\n" +
                "\t\t2) Search By Plate\n" +
                "\t\t3) Set Minimum Price\n" +
                "\t\t4) Set Maximum Price\n" +
                "\t\t5) Buy a Car\n" +
                "\t\t6) Exit Application\n" +
                "Goodbye!\n");
    }

    /**
     * Tests the Car class integrating with the Algorithm Engineers AELicensePlateChecker class.
     */
    @Test
    public void IntegrationTest1() {
        LicensePlateCheckerInterface checker = new AELicensePlateChecker();
        Assertions.assertTrue(checker.validate(testCar.getPlate()));
    }
    /**
     * Tests the Car and CarLoader classes integrating with the Algorithm Engineers IterableRedBlackTree
     * and Backend Engineers Backend.
     */
    @Test
    public void IntegrationTest2() throws FileNotFoundException{
        IterableRBTADT<ICar> rbt = new AERedBlackTree<ICar>();
        CarLoader loader = new CarLoader();
        ArrayList<ICar> cars = loader.loadCars("cars1.xml");
        BackendInterface backend = new Backend(rbt);
        backend.addCar(testCar);
        Assertions.assertTrue(backend.searchByLicensePlate("123ABC").equals(testCar));
    }
    /**
     * Tests the getters of the Car class.
     */
    @Test
    public void test1() {
        Assertions.assertEquals("123ABC",testCar.getPlate());
        Assertions.assertEquals("2022",testCar.getYear());
        Assertions.assertEquals("Honda",testCar.getMake());
        Assertions.assertEquals("CR-V",testCar.getModel());
        Assertions.assertEquals("12345",testCar.getPrice());

    }

    /**
     * Tests the toString of the Car class.
     */
    @Test
    public void test2() {
        Assertions.assertEquals(testCar.toString(),"2022 Honda CR-V, Plate: 123ABC, Price: $12345");
        Assertions.assertTrue(true);
    }

    /**
     * Checks a few cars from the CarLoader to see if they are correct.
     */
    @Test
    public void test3() {
        Assertions.assertEquals(cars.get(66).toString(), "2008 Mitsubishi Lancer Evolution X GSR \"Welcome Pack\", Plate: 129FFE, Price: $20663");
        Assertions.assertEquals(cars.get(4).toString(), "1991 Hoonigan Rauh-Welt Begriff Porsche 911 Turbo, Plate: 081AMW, Price: $13043");
    }

    /**
     *Tests if comparing ICars works, they should compare by license plate.
     */
    @Test
    public void test4() {
        Assertions.assertTrue(testCar.compareTo(testCar2)<0);
        Assertions.assertTrue(testCar.compareTo(testCar)==0);
        Assertions.assertTrue(testCar2.compareTo(testCar)>0);
    }

    /**
     *
     */
    @Test
    public void test5() throws FileNotFoundException{
        Assertions.assertEquals(null, loader.loadCars("cars.xml"));
    }

}

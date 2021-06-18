package com.zipc.garden.webplatform.server;


/**
 * This class manages dummy data.
 */
public interface DummyData {

    /**
     * Dummy data of CSC file contents
     */
    public static byte[] CSC_FILE_DUMMY_DATA = new String("Scenario CIDASCrash3 {\r\n" + 
            "    direction:left\r\n" + 
            "    // Ego Car\r\n" + 
            "    EgoCar {\r\n" + 
            "        model:Sedan\r\n" + 
            "        Routes {\r\n" + 
            "            Start {\r\n" + 
            "                lane:Lane2410\r\n" + 
            "                shift:5\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane810\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane709\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Goal {\r\n" + 
            "                lane:Lane609\r\n" + 
            "                shift:-1\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    \r\n" + 
            "    // Other Car\r\n" + 
            "    Object OtherCar1 {\r\n" + 
            "        Routes {\r\n" + 
            "            accel: 2000.0\r\n" + 
            "            Start {\r\n" + 
            "                lane:Lane810\r\n" + 
            "                shift:15\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane709\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane609\r\n" + 
            "                lanechange_start:16\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Goal {\r\n" + 
            "                lane:Lane608\r\n" + 
            "                lanechange_end:31\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Object OtherCar2 {\r\n" + 
            "        Routes {\r\n" + 
            "            Start {\r\n" + 
            "                lane:Lane609\r\n" + 
            "                lanechange_start:48\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Goal {\r\n" + 
            "                lane:Lane608\r\n" + 
            "                lanechange_end:50\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Object OtherCar3 {\r\n" + 
            "        Routes {\r\n" + 
            "            Start {\r\n" + 
            "                lane:Lane2301\r\n" + 
            "                shift:5\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane2501\r\n" + 
            "                lanechange_start:10\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane2502\r\n" + 
            "                lanechange_end:20\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane2602\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane2902\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane3102\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Goal {\r\n" + 
            "                lane:Lane3202\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Object OtherCar4 {\r\n" + 
            "        Routes {\r\n" + 
            "            Start {\r\n" + 
            "                lane:Lane201\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane301\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Mid {\r\n" + 
            "                lane:Lane401\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "            Goal {\r\n" + 
            "                lane:Lane501\r\n" + 
            "                velocity:80.00\r\n" + 
            "            }\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Object OtherCar5 {\r\n" + 
            "        WayPoint {\r\n" + 
            "          accel: 2000.0\r\n" + 
            "          Point {x:310 y:1 z:0}\r\n" + 
            "          Point {x:320 y:1 z:1}\r\n" + 
            "          Point {x:330 y:1 z:2}\r\n" + 
            "          Point {x:340 y:1 z:3}\r\n" + 
            "          Point {x:350 y:1 z:4}\r\n" + 
            "          Point {x:360 y:1 z:5}\r\n" + 
            "          Point {x:370 y:1 z:6}\r\n" + 
            "          Point {x:380 y:1 z:7}\r\n" + 
            "          Point {x:390 y:1 z:8}\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    \r\n" + 
            "    // ROAD\r\n" + 
            "    Road Road100 {\r\n" + 
            "        length:114\r\n" + 
            "        type:circular\r\n" + 
            "        radius:8000.00\r\n" + 
            "        connection:Lane1404\r\n" + 
            "        Lane Lane101 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane102 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane103 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road200 {\r\n" + 
            "        type:clothoid_out\r\n" + 
            "        connection:Road100\r\n" + 
            "        length:75\r\n" + 
            "        radius:-441.00\r\n" + 
            "        Lane Lane201 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane202 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane203 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road300 {\r\n" + 
            "        type:clothoid_in\r\n" + 
            "        connection:Road200\r\n" + 
            "        length:37\r\n" + 
            "        radius:26.00\r\n" + 
            "        height: -4\r\n" + 
            "        ramp_angle: 12\r\n" + 
            "        Lane Lane301 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane302 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane303 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road400 {\r\n" + 
            "        type:circular\r\n" + 
            "        connection:Road300\r\n" + 
            "        length:42\r\n" + 
            "        radius:26.00\r\n" + 
            "        Lane Lane401 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane402 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane403 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road500 {\r\n" + 
            "        type:clothoid_out\r\n" + 
            "        connection:Road400\r\n" + 
            "        length:35\r\n" + 
            "        radius:22.00\r\n" + 
            "        Lane Lane501 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane502 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane503 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road600 {\r\n" + 
            "        connection:Road1400\r\n" + 
            "        length:238\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-2154.00\r\n" + 
            "        Lane Lane601 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane602 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane603 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane604 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane605 {\r\n" + 
            "            width:2.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane606 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane607 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane608 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane609 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane610 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane611 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road700 {\r\n" + 
            "        connection:Road600\r\n" + 
            "        length:142\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane701 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane702 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane703 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane704 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane705 {\r\n" + 
            "            width:2.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane706 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane707 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane708 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane709 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane710 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane711 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road800 {\r\n" + 
            "        connection:Road700\r\n" + 
            "        length:80\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane801 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane802 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane803 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane804 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane805 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane806 {\r\n" + 
            "            width:2.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane807 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane808 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane809 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane810 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane811 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road900 {\r\n" + 
            "        connection:Lane2405\r\n" + 
            "        predecessor\r\n" + 
            "        length:75\r\n" + 
            "        type:circular\r\n" + 
            "        radius:893.00\r\n" + 
            "        Lane Lane901 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane902 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane903 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1000 {\r\n" + 
            "        connection:Road900\r\n" + 
            "        predecessor\r\n" + 
            "        length:55\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-780.00\r\n" + 
            "        Lane Lane1001 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1002 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1003 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1100 {\r\n" + 
            "        connection:Road1000\r\n" + 
            "        predecessor\r\n" + 
            "        length:54\r\n" + 
            "        type:circular\r\n" + 
            "        radius: 63.00\r\n" + 
            "        height: -3.1\r\n" + 
            "        ramp_angle:8.7\r\n" + 
            "        Lane Lane1101 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1102 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1103 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1200 {\r\n" + 
            "        connection:Road1100\r\n" + 
            "        predecessor\r\n" + 
            "        length:42\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane1201 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1202 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1203 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1300 {\r\n" + 
            "        type:clothoid_in\r\n" + 
            "        connection:Lane1409\r\n" + 
            "        length:44\r\n" + 
            "        radius:-221.00\r\n" + 
            "        Lane Lane1301 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1302 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1303 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1400 {\r\n" + 
            "        length:100\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane1401 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1402 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1403 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1404 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1405 {\r\n" + 
            "            width:2.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1406 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1407 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1408 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1409 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1410 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1411 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1500 {\r\n" + 
            "        connection:Road1300\r\n" + 
            "        length:64\r\n" + 
            "        type:circular\r\n" + 
            "        radius:1654.00\r\n" + 
            "        Lane Lane1501 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1502 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1503 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1600 {\r\n" + 
            "        connection:Road1500\r\n" + 
            "        length:62\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-67.00\r\n" + 
            "        height: -0.4\r\n" + 
            "        ramp_angle: 3.6\r\n" + 
            "        Lane Lane1601 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1602 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1603 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1700 {\r\n" + 
            "        type: clothoid_out\r\n" + 
            "        connection:Road1600\r\n" + 
            "        length:18\r\n" + 
            "        radius:-133.00\r\n" + 
            "        Lane Lane1701 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1702 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1703 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1800 {\r\n" + 
            "        connection:Road1700\r\n" + 
            "        length:15\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane1801 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1802 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1803 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road1900 {\r\n" + 
            "        type:clothoid_in\r\n" + 
            "        connection:Road1800\r\n" + 
            "        length:15\r\n" + 
            "        radius:21.00\r\n" + 
            "        Lane Lane1901 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1902 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane1903 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2000 {\r\n" + 
            "        connection:Road1900\r\n" + 
            "        length:64\r\n" + 
            "        type:circular\r\n" + 
            "        radius:45.00\r\n" + 
            "        Lane Lane2001 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2002 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2003 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2100 {\r\n" + 
            "        connection:Lane809\r\n" + 
            "        predecessor\r\n" + 
            "        length:43\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-285.00\r\n" + 
            "        Lane Lane2101 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2102 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2103 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2104 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2200 {\r\n" + 
            "        connection:Road2100\r\n" + 
            "        predecessor\r\n" + 
            "        length:51\r\n" + 
            "        type:circular\r\n" + 
            "        radius:380.00\r\n" + 
            "        Lane Lane2201 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2202 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2203 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2204 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2300 {\r\n" + 
            "        type:clothoid_out\r\n" + 
            "        connection:Road2200\r\n" + 
            "        predecessor\r\n" + 
            "        length:76\r\n" + 
            "        radius:-166.00\r\n" + 
            "        Lane Lane2301 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2302 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2303 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2304 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2400 {\r\n" + 
            "        connection:Road800\r\n" + 
            "        length:10\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane2401 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2402 {\r\n" + 
            "            width:2.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2403 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2404 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2405 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2406 {\r\n" + 
            "            width:2.50 \r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2407 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2408 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2409 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2410 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2411 {\r\n" + 
            "            width:2.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2500 {\r\n" + 
            "        connection:Road2300\r\n" + 
            "        predecessor\r\n" + 
            "        length:94\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-31.00\r\n" + 
            "        height: -2.5\r\n" + 
            "        ramp_angle: 4.5\r\n" + 
            "        Lane Lane2501 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2502 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2503 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2504 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2600 {\r\n" + 
            "        connection:Road2500\r\n" + 
            "        predecessor\r\n" + 
            "        length:31\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-41.00\r\n" + 
            "        Lane Lane2601 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2602 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2603 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2604 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2700 {\r\n" + 
            "        connection:Road1200\r\n" + 
            "        predecessor\r\n" + 
            "        length:45\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-60.00\r\n" + 
            "        Lane Lane2701 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2702 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2703 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2800 {\r\n" + 
            "        connection:Road2700\r\n" + 
            "        predecessor\r\n" + 
            "        length:25\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-92.00\r\n" + 
            "        Lane Lane2801 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2802 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2803 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road2900 {\r\n" + 
            "        connection:Road2600\r\n" + 
            "        predecessor\r\n" + 
            "        length:44\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-60.00\r\n" + 
            "        Lane Lane2901 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2902 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2903 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane2904 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3000 {\r\n" + 
            "        connection:Road2900\r\n" + 
            "        predecessor\r\n" + 
            "        length:68\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-215.00\r\n" + 
            "        Lane Lane3001 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3002 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3003 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3004 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3100 {\r\n" + 
            "        connection:Road3000\r\n" + 
            "        predecessor\r\n" + 
            "        length:67\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-58.00\r\n" + 
            "        Lane Lane3101 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3102 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3103 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3104 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3200 {\r\n" + 
            "        connection:Road3100\r\n" + 
            "        predecessor\r\n" + 
            "        length:14\r\n" + 
            "        type:circular\r\n" + 
            "        radius:-54.00\r\n" + 
            "        Lane Lane3201 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3202 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3203 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3204 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3300 {\r\n" + 
            "        connection:Road2000\r\n" + 
            "        length:47\r\n" + 
            "        type:circular\r\n" + 
            "        radius:62.00\r\n" + 
            "        Lane Lane3301 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3302 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3303 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3400 {\r\n" + 
            "        connection:Road3300\r\n" + 
            "        length:76\r\n" + 
            "        type:circular\r\n" + 
            "        radius:254.00\r\n" + 
            "        Lane Lane3401 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3402 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3403 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3500 {\r\n" + 
            "        connection:Road3400\r\n" + 
            "        length:78\r\n" + 
            "        type:circular\r\n" + 
            "        radius:53.00\r\n" + 
            "        Lane Lane3501 {\r\n" + 
            "            width:3.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3502 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3503 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    Road Road3600 {\r\n" + 
            "        connection:Road3500\r\n" + 
            "        length:10\r\n" + 
            "        type:straight\r\n" + 
            "        radius:0.00\r\n" + 
            "        Lane Lane3601 {\r\n" + 
            "            width:9.80\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3602 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:left\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3603 {\r\n" + 
            "            width:1.00\r\n" + 
            "            position:center\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3604 {\r\n" + 
            "            width:13.50\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "        Lane Lane3605 {\r\n" + 
            "            width:0.70\r\n" + 
            "            position:right\r\n" + 
            "            type:driving\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "    \r\n" + 
            "    Model {\r\n" + 
            "        Car Sedan {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car ObsMixer_1 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar55 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar19 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar22 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar25 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar27 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "        Car OtherCar18 {\r\n" + 
            "            width:-1\r\n" + 
            "            height:-1\r\n" + 
            "            length:-1\r\n" + 
            "            wheelbase:-1\r\n" + 
            "        }\r\n" + 
            "    }\r\n" + 
            "}\r\n" + 
            "\r\n" + 
            "\r\n" + 
            "").getBytes();

}

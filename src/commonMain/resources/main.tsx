<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.5" tiledversion="1.7.0" name="Main" tilewidth="16" tileheight="16" tilecount="4096" columns="64">
 <image source="Tileset.png" width="1024" height="1024"/>
 <tile id="0">
  <properties>
   <property name="terrain" value="Grass"/>
  </properties>
 </tile>
 <tile id="1">
  <properties>
   <property name="terrain" value="Sand"/>
  </properties>
 </tile>
 <tile id="8">
  <properties>
   <property name="terrain" value="Dirt"/>
  </properties>
 </tile>
 <tile id="74">
  <properties>
   <property name="terrain" value="Door"/>
  </properties>
 </tile>
 <tile id="75">
  <properties>
   <property name="terrain" value="Door"/>
  </properties>
 </tile>
 <tile id="129">
  <properties>
   <property name="terrain" value="Bush"/>
  </properties>
 </tile>
 <tile id="132">
  <properties>
   <property name="terrain" value="Bush"/>
  </properties>
 </tile>
 <tile id="135">
  <properties>
   <property name="terrain" value="Wall"/>
  </properties>
 </tile>
 <tile id="136">
  <properties>
   <property name="terrain" value="Wall"/>
  </properties>
 </tile>
 <tile id="137">
  <properties>
   <property name="terrain" value="Wall"/>
  </properties>
 </tile>
 <tile id="192">
  <properties>
   <property name="terrain" value="Boulder"/>
  </properties>
 </tile>
 <tile id="320">
  <properties>
   <property name="terrain" value="Grass"/>
  </properties>
  <animation>
   <frame tileid="320" duration="500"/>
   <frame tileid="321" duration="500"/>
   <frame tileid="322" duration="500"/>
   <frame tileid="323" duration="500"/>
  </animation>
 </tile>
 <tile id="448">
  <properties>
   <property name="terrain" value="Grass"/>
  </properties>
  <animation>
   <frame tileid="448" duration="500"/>
   <frame tileid="449" duration="500"/>
   <frame tileid="450" duration="500"/>
   <frame tileid="451" duration="500"/>
  </animation>
 </tile>
 <tile id="845">
  <properties>
   <property name="terrain" value="Water"/>
  </properties>
  <animation>
   <frame tileid="1792" duration="500"/>
   <frame tileid="1793" duration="500"/>
   <frame tileid="1794" duration="500"/>
   <frame tileid="1795" duration="500"/>
  </animation>
 </tile>
 <tile id="961">
  <properties>
   <property name="terrain" value="Boulder"/>
  </properties>
 </tile>
 <tile id="1024">
  <properties>
   <property name="terrain" value="Dirt"/>
  </properties>
 </tile>
 <tile id="1025">
  <properties>
   <property name="terrain" value="Boulder"/>
  </properties>
 </tile>
 <tile id="1792">
  <properties>
   <property name="terrain" value="Water"/>
  </properties>
  <animation>
   <frame tileid="1792" duration="500"/>
   <frame tileid="1793" duration="500"/>
   <frame tileid="1794" duration="500"/>
   <frame tileid="1795" duration="500"/>
  </animation>
 </tile>
 <wangsets>
  <wangset name="Unnamed Set" type="corner" tile="-1">
   <wangcolor name="sand" color="#ff0000" tile="-1" probability="1"/>
   <wangcolor name="grass" color="#00ff00" tile="-1" probability="1"/>
   <wangtile tileid="0" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="1" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="588" wangid="0,2,0,1,0,2,0,2"/>
   <wangtile tileid="589" wangid="0,2,0,1,0,1,0,2"/>
   <wangtile tileid="590" wangid="0,2,0,2,0,1,0,2"/>
   <wangtile tileid="652" wangid="0,1,0,1,0,2,0,2"/>
   <wangtile tileid="653" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="654" wangid="0,2,0,2,0,1,0,1"/>
   <wangtile tileid="716" wangid="0,1,0,2,0,2,0,2"/>
   <wangtile tileid="717" wangid="0,1,0,2,0,2,0,1"/>
   <wangtile tileid="718" wangid="0,2,0,2,0,2,0,1"/>
  </wangset>
 </wangsets>
</tileset>

import 'package:calm_weather_app/theme.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      //theme: WeatherTheme(),
      theme: ThemeData.dark(),
      home: Scaffold(
        appBar: AppBar(title: Center(child: Text("Ростов-на-Дону"))),
        body: MainContainer()
      ),
      //home: MainContainer(),
    );
  }
}

class MainContainer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      decoration: BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [ Colors.deepPurple.shade900, Colors.black]
        )
      ),
      child: Text('test'),
    );
  }
}



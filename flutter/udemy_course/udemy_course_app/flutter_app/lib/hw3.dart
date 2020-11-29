/*
  Интерфейс погодого приложнения.
  Домашка по курсу udemy.
 */

import 'package:flutter/material.dart';

const bgColor = Colors.deepOrange;
const bodyColor = Colors.white;
const bodyTextColorStyle = TextStyle(color: bodyColor);

class WeatherMainScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: _appBar(),
        body: WeatherWidget(),
      ),
    );
  }
}

Widget _appBar() {
  return AppBar(
    title: Text('Weather Forecast'),
    centerTitle: true,
    backgroundColor: bgColor,
  );
}

class WeatherWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: double.infinity, // Внешний контейнер бесконечной высоты
        padding: EdgeInsets.all(10),
        color: bgColor,
        child: SingleChildScrollView( // а скрол уже внутри
          child: Column(
            children: [
              SearchBar(),
              SizedBox(height: 50),
              CityInfo(),
              SizedBox(height: 50),
              WeatherInfo(),
              SizedBox(height: 50),
              AdditionalInfo(),
              SizedBox(height: 50),
              Forecast(),
            ],
          ),
        ));
  }
}

class Forecast extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text('7-DAY WEATHER FORECAST', style: TextStyle(fontSize: 18, color: bodyColor),),
        SizedBox(
          height: 100,
          child: ListView(
            scrollDirection: Axis.horizontal,
            children: [
              DayForecast('Sunday', '6 °F', Icons.wb_sunny),
              DayForecast('Monday', '5 °F', Icons.wb_cloudy),
              DayForecast('Friday', '2 °F', Icons.ac_unit_sharp),
              DayForecast('Tuesday', '0 °F', Icons.ac_unit_sharp),
              DayForecast('Wednesday', '1 °F', Icons.ac_unit_sharp),
              DayForecast('Thursday', '4 °F', Icons.wb_cloudy),
              DayForecast('Saturday', '8 °F', Icons.wb_sunny),
            ],
          ),
        )
      ],
    );
  }
}

class DayForecast extends StatelessWidget {
  String day;
  String forecast;
  IconData icon;

  DayForecast(this.day, this.forecast, this.icon);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(5),
      padding: EdgeInsets.all(5),
      width: 120,
      height: 80,
      color: Colors.deepOrange[300],
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Text(day, style: TextStyle(fontSize: 20, color: bodyColor),),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(forecast, style: TextStyle(fontSize: 20, color: bodyColor),),
              SizedBox(width: 5,),
              Icon(icon, color: bodyColor,)
            ],
          )
        ],
      ),
    );
  }
}

class AdditionalInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Column(
              children: [
                Icon(Icons.ac_unit_sharp, color: bodyColor,),
                Text('5', style: bodyTextColorStyle,),
                Text('km/h', style: bodyTextColorStyle,)
              ],
            ),
            Column(
              children: [
                Icon(Icons.ac_unit_sharp, color: bodyColor,),
                Text('3', style: bodyTextColorStyle,),
                Text('%', style: bodyTextColorStyle,)
              ],
            ),
            Column(
              children: [
                Icon(Icons.ac_unit_sharp, color: bodyColor,),
                Text('20', style: bodyTextColorStyle,),
                Text('%', style: bodyTextColorStyle,)
              ],
            ),
          ],
        ));
  }
}

class WeatherInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.wb_sunny,
              color: bodyColor,
              size: 60,
            ),
            SizedBox(
              width: 20,
            ),
            Column(
              children: [
                Text(
                  '14 °F',
                  style: TextStyle(color: bodyColor, fontSize: 40),
                ),
                Text(
                  'LIGHT SNOW',
                  style: TextStyle(color: bodyColor, fontSize: 20),
                ),
              ],
            )
          ],
        ));
  }
}

class CityInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          'Rostov oblast, RU',
          style: TextStyle(fontSize: 30, color: bodyColor),
        ),
        Text('Saturday, Nov 28, 2020',
            style: TextStyle(fontSize: 18, color: bodyColor)),
      ],
    );
  }
}

class SearchBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(
          Icons.search,
          color: bodyColor,
        ),
        Text(
          'Enter city name',
          style: bodyTextColorStyle,
        )
      ],
    );
  }
}

import 'dart:async';

import 'package:flutter/material.dart';

import 'form.dart';
import 'http.dart';
import 'hw2.dart';
import 'hw3.dart';
import 'layouts.dart';
import 'list_view.dart';
import 'name_navigation.dart';
import 'navigation.dart';

void main() {
  //runApp(MainWidget());
  //runApp(CounterWidget()); // Домашнее задание с счетчиком
  //runApp(LayoutExampleWidget()); // Пример использования layout виджетов
  //runApp(ListViewExampleWidget()); // Пример использования ListView
  //runApp(WeatherMainScreen()); // Домашнее задание - верстка погодного приложения
  //runApp(NavigationExampleWidget()); // Пример использования навигации
  //runApp(NamedNavigationExampleWidget()); // Пример использования навигации
  //runApp(FormExampleWidget()); // Пример использования навигации
  runApp(HttpExampleWidget()); // Пример взаимодействия с сетью
}

class MainWidget extends StatefulWidget {
  @override
  _MainWidgetState createState() => _MainWidgetState();
}

class _MainWidgetState extends State<MainWidget> {
  bool _loading;
  double _progress;

  @override
  void initState() {
    _loading = false;
    _progress = 0.0;

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(fontFamily: 'SansitaSwashed'),
      home: Scaffold(
          appBar: AppBar(
            leading: Icon(Icons.terrain_outlined),
            title: Text(
              "My First App",
            ),
            centerTitle: true,
          ),
          floatingActionButton: FloatingActionButton(
            child: Icon(Icons.cloud_download),
            onPressed: () {
              setState(() {
                _loading = !_loading;
                _updateProgress();
              });
            },
          ),
          body: Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                  image: AssetImage('assets/images/bg_image.png'),
                  fit: BoxFit.cover),
            ),
            padding: EdgeInsets.all(16),
            child: _loading
                ? Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      LinearProgressIndicator(
                        value: _progress,
                      ),
                      Text(
                        "${(_progress * 100).round()} %",
                        style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                            color: Colors.indigo),
                      ),
                    ],
                  )
                : Column(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                        Text(
                          "Press the button",
                          style: TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.bold,
                              color: Colors.indigo),
                        ),
                        // Или укороченно так: Image.asset(name)
                        Image(
                            width: double.infinity,
                            image: AssetImage('assets/icons/cloud_icon.png')),
                      ]),
          )), /**/
    );
  }

  void _updateProgress() {
    Timer.periodic(Duration(seconds: 1), (timer) {
      setState(() {
        _progress += 0.2;
        if (_progress == 1.0) {
          timer.cancel();
          _progress = 0.0;
          _loading = false;
        }
      });
    });
  }
}

///**/

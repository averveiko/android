import 'dart:async';

import 'package:flutter/material.dart';

void main() {
  runApp(MainWidget());
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
      home: Scaffold(
          appBar: AppBar(
            title: Text("My First App"),
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
                : Center(
              child: Text(
                "Press the button",
                style: TextStyle(
                    fontSize: 15,
                    fontWeight: FontWeight.bold,
                    color: Colors.indigo),
              ),
            ),
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

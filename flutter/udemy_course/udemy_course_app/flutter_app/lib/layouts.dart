import 'package:flutter/material.dart';

class LayoutExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text('Layout example'),
            centerTitle: true,
          ),
          body: Row(
            children: [
              Expanded(
                flex: 2,
                child: Container(
                  padding: EdgeInsets.all(30),
                  color: Colors.red,
                  child: Text('1'),
                ),
              ),
              Expanded(
                flex: 3,
                child: Container(
                  padding: EdgeInsets.all(30),
                  color: Colors.green,
                  child: Text('2'),
                ),
              ),
              Expanded(
                child: Container(
                  padding: EdgeInsets.all(30),
                  color: Colors.blue,
                  child: Text('3'),
                ),
              ),
            ],
          ),
      ),
    );
  }
}

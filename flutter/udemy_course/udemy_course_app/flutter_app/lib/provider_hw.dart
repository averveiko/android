import 'dart:math';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class MyProviderHWApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Provider Demo',
      home: ChangeNotifierProvider<SwitchColorProvider>.value(
        value: SwitchColorProvider(),
        child: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.black,
            title: Consumer<SwitchColorProvider>(
              builder: (BuildContext context, value, Widget child) {
                return Text('Homework Provider',
                    style: TextStyle(
                        fontWeight: FontWeight.bold, color: value.currentColor
                    )
                );
              }
            ),
            centerTitle: true,
          ),
          body: Center(
            child: Consumer<SwitchColorProvider>(
              builder: (BuildContext context, value, Widget child) {
                return Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    AnimatedContainer(
                      width: 200,
                      height: 200,
                      duration: Duration(seconds: 1),
                      color: value.currentColor,
                    ),
                    Switch(value: value._value, onChanged: (_) => value._changeValue())
                  ],
                );
              }
            ),
          ),
        ),
      ),
    );
  }
}

class SwitchColorProvider extends ChangeNotifier {
  static Random rnd = Random();

  bool _value = false;
  Color _color =  Colors.yellowAccent;

  bool get switchValue => _value;
  Color get currentColor => _color;

  void _changeValue() {
    _value = !_value;
    _color = _getRandomColor();
    notifyListeners();
  }

  Color _getRandomColor() {
    return Colors.primaries[rnd.nextInt(Colors.primaries.length)];
  }
}

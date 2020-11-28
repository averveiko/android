
import 'package:flutter/material.dart';

class CounterWidget extends StatelessWidget {
  var _textStyle = TextStyle(color: Colors.indigo[100]);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Home Work 2'), centerTitle: true, backgroundColor: Colors.indigo,),
        body: Container(
          decoration: BoxDecoration(color: Colors.indigo[300]),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Tap "-" to decrement', style: _textStyle,),
                StatefulCounter(),
                Text('Tap "+" to increment', style: _textStyle,),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class StatefulCounter extends StatefulWidget {

  @override
  _StatefulCounterState createState() => _StatefulCounterState();
}

class _StatefulCounterState extends State<StatefulCounter> {
  int _count;

  @override
  void initState() {
    _count = 50;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(5),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.all(Radius.circular(10)),
        color: Colors.indigo[100],
      ),
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          IconButton(icon: Icon(Icons.remove), onPressed: onDecPressed,),
          Text('${_count}'),
          IconButton(icon: Icon(Icons.add), onPressed: onIncPressed,),
        ],
      ),
    );
  }

  void onDecPressed() {
    setState(() {
      _count-=1;
    });
  }

  void onIncPressed() {
    setState(() {
      _count+=1;
    });
  }
}


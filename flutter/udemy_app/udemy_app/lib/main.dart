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
        home: Scaffold(
          appBar: AppBar(title: Text('Udemy example')),
          //body: Example(),
          //body: StackWidget(),
          body: IndexedStackWidget(),
        ));
  }
}

class Example extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [BackGroundImage(), AboutIsland(), HeartIcon()],
    );
  }
}

class StackWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Распологает виджеты друг на друге
    return Stack(
      alignment: Alignment.center,
      clipBehavior: Clip.none,
      // По дефолту размеры минимальные, expand раскукоживает по максимуму
      fit: StackFit.expand,
      children: [
        BackGroundImage(),
        Positioned(top: 300, child: AboutIsland()),
        // Сердечко хотим отпозиционировать отдельно
        Positioned(right: 70, top: 310, child: HeartIcon())
      ],
    );
  }
}

class IndexedStackWidget extends StatefulWidget {
  @override
  _IndexedStackWidgetState createState() => _IndexedStackWidgetState();
}

class _IndexedStackWidgetState extends State<IndexedStackWidget> {
  int _index = 0;
  double size = 100.0;

  void _switchLayout() {
    if (_index != 2) {
      setState(() {
        _index++;
        size += 100.0;
      });
    } else {
      setState(() {
        _index = 0;
        size = 50.0;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          IndexedStack(
            index: _index,
            children: [
              AnimatedSquare(size, Colors.redAccent),
              AnimatedSquare(size, Colors.orangeAccent),
              AnimatedSquare(size, Colors.greenAccent),
            ],
          ),
          RaisedButton(
            onPressed: () {
              _switchLayout();
            },
            color: Colors.black,
            textColor: Colors.white,
            child: Text('Color button'),
          )
        ],
      ),
    );
  }
}

class AnimatedSquare extends StatelessWidget {
  final double size;
  final Color color;

  AnimatedSquare(this.size, this.color);

  @override
  Widget build(BuildContext context) {
    return AnimatedContainer(
      curve: Curves.easeInBack,
      duration: Duration(seconds: 1),
      width: size,
      height: size,
      color: color,
    );
  }
}

class BackGroundImage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      // Чтобы заработало отредактируй pubspec.yaml
      child: Image.asset('assets/images/bali.jpg', fit: BoxFit.cover),
    );
  }
}

class AboutIsland extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      height: 180,
      decoration: BoxDecoration(
        color: Colors.grey[200],
        borderRadius: BorderRadius.circular(15),
      ),
      child: Column(
        children: [
          SizedBox(height: 20),
          Text(
            'Остров Бали',
            style: TextStyle(
                fontSize: 30, color: Colors.black, fontWeight: FontWeight.w600),
          ),
          SizedBox(height: 10),
          Container(
            padding: EdgeInsets.all(10),
            child: Text(
              'Бали - настоящая сказка наяву, прекрасный стров,покрытый великолепными растениями.',
              style: TextStyle(fontSize: 12, color: Colors.black),
            ),
          )
        ],
      ),
    );
  }
}

class HeartIcon extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Icon(
      Icons.favorite,
      color: Colors.red,
      size: 30,
    );
  }
}

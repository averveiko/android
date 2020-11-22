import 'package:flutter/material.dart';

void main() {
  runApp(MainApp());
}

class MainApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Info project',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Info project'),
        ),
        body: ContainerDemo(),
      ),
    );
  }
}

class ContainerDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final imgBambookUrl = 'https://oboi-ru.ru/upload/iblock/7f2/7f2d60a38ae5a5887318b6d9df8c413a.jpg';
    return Center(
      child: Container(
        //color: Colors.green,
        // Заполнить по ширине width: double.infinity
        width: 200,
        height: 200,
        // Выравнивает дочерний элемент по центру контейнера
        alignment: Alignment.center,
        // Внешний отступ
        margin: EdgeInsets.all(20),
        // Отступы внутри контейнера //EdgeInsets.all(50)
        // padding: EdgeInsets.only(left: 50, top: 50),
        // Повернуть контейнер
        //transform: Matrix4.rotationZ(0.1),
        // Красивости
        decoration: BoxDecoration(

          // Хотим вообще круг, а не квадрат по дефолту
          shape: BoxShape.circle,
            boxShadow: [
              BoxShadow(
                // Цвет тени
                color: Colors.black45,
                // Смещение
                offset: Offset(-5, 5),
                // Радиус размытия
                blurRadius: 5,
                // Величина, на которую должен быть увеличен контейнер перед применением размытия ???
                spreadRadius: 5
              )
            ],
            // Скруглить углы
            // borderRadius: BorderRadius.circular(15),
            // Задать рамку контейнеру
            /*border: Border.all(
              color: Colors.black,
              width: 8
            ),*/
            /*
            Градиент можно запилить
            gradient: LinearGradient(
            colors: [Colors.red, Colors.cyan]
          )*/
            // color: Colors.green,
            // Подложим картинку на фон
            image: DecorationImage(
                image: Image
                    .network(imgBambookUrl)
                    .image,
                // чтобы не осталось незаполненных картинкой областей
                fit: BoxFit.cover
            )
        ),
        child: Text(
          'Panda',
          style: TextStyle(
              fontSize: 40,
              color: Colors.white70
          ),
        ),
      ),
    );
  }
}

class DemoStatefulWidget extends StatefulWidget {
  @override
  _DemoStatefulWidgetState createState() => _DemoStatefulWidgetState();
}

class _DemoStatefulWidgetState extends State<DemoStatefulWidget> {
  int _count = 0;

  void _handleButton() {
    setState(() {
      _count++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(

        /// Place the children as close to the middle of the main axis as possible.
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text('$_count'),
          RaisedButton(
            onPressed: () {
              _handleButton();
            },
            child: Text('Press me'),
          )
        ],
      ),
    );
  }
}

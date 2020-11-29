import 'package:flutter/material.dart';

class NamedNavigationExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(initialRoute: '/',
        /*
       Еще есть onGenerateRoute но я не понял нахрена он если обычный routes более удобен.
       Единственно onGenerateRoute позволяет накрутить бизнес логики сверху.
       */
        routes: {
          '/page2': (context) => Page2(),
          '/': (context) => HomePage(),
        });
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.terrain_outlined),
        title: Text(
          "Named Routing and Navigation",
        ),
        centerTitle: true,
      ),
      body: TextAndButtonWidget(),
    );
  }
}

class TextAndButtonWidget extends StatefulWidget {
  @override
  _TextAndButtonWidgetState createState() => _TextAndButtonWidgetState();
}

class _TextAndButtonWidgetState extends State<TextAndButtonWidget> {
  String _data;

  @override
  void initState() {
    _data = 'empty';
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(_data),
            RaisedButton(
              onPressed: () {
                _returnDataFromSecondScreen(context);
              },
              child: Text('Move to Page 2'),
            ),
          ],
        ),
      ),
    );
  }

  void _returnDataFromSecondScreen(BuildContext context) async {
    User user = User(name: 'Aleksandr', age: 35);
    final result =
        await Navigator.pushNamed(context, '/page2', arguments: user);

    // Установим полученный ответ как текст
    setState(() {
      _data = result;
    });
  }
}

class Page2 extends StatelessWidget {
  User user;

  @override
  Widget build(BuildContext context) {
    // Вытаскиваем переданные из первого экрана данные
    RouteSettings settings = ModalRoute.of(context).settings;
    user = settings.arguments;

    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Page 2",
        ),
        centerTitle: true,
      ),
      body: Container(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('User:'),
              Text('${user.name}'),
              Text('${user.age}'),
              RaisedButton(
                onPressed: () {
                  String textToSendBack = 'returned text';
                  Navigator.pop(context, textToSendBack);
                },
                child: Text('Back to previous page'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class User {
  final String name;
  final int age;

  User({this.name, this.age});
}

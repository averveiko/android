import 'package:flutter/material.dart';

class NavigationExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              leading: Icon(Icons.terrain_outlined),
              title: Text(
                "Routing and Navigation",
              ),
              centerTitle: true,
            ),
            body: HomePage()));
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: RaisedButton(
          onPressed: () {
            User user = User(name: 'Alexander', age: 35);
            Route route = MaterialPageRoute(builder: (context) => Page2(user: user));
            Navigator.push(context, route);
          },
          child: Text('Move to Page 2'),
        ),
      ),
    );
  }
}

class Page2 extends StatelessWidget {
  User user;

  Page2({this.user});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
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
                    Navigator.pop(context);
                  },
                  child: Text('Back to previous page'),
                ),
              ],
            ),
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
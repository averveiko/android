import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';

class MyProviderDemoApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Provider Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MultiProvider(
        providers: [
          ChangeNotifierProvider<CountProvider>.value(value: CountProvider()),
          FutureProvider(create: (_) async => UserProvider().loadUserData()),
          StreamProvider(create: (_)  => EventProvider().intStream(), initialData: 0,)
        ],
        child: DefaultTabController(
          length: 3,
          child: Scaffold(
            appBar: AppBar(
              title: Text('Provider Demo'),
              centerTitle: true,
              bottom: TabBar(
                tabs: [
                  Tab(icon: Icon(Icons.add)),
                  Tab(icon: Icon(Icons.person)),
                  Tab(icon: Icon(Icons.message)),
                ],
              ),
            ),
            body: TabBarView(
              children: [MyCountPage(), MyUserPage(), MyEventPage()],
            ),
          ),
        ),
      ),
    );
  }
}

//////////////////////////////////////////////////////////////
class MyCountPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    CountProvider _state = Provider.of<CountProvider>(context);

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('ChangeNotifierProvider Example'),
            SizedBox(height: 50),
            Text('${_state.counterValue}'),
            ButtonBar(
              alignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  // Первый вариант
                  icon: Icon(Icons.remove), onPressed: () => _state._dec(),
                ),
                // Второй вариант - обернуть в Consumer
                Consumer<CountProvider>(
                  builder: (BuildContext context, value, Widget child) {
                    return IconButton(
                      icon: Icon(Icons.add),
                      onPressed: () => value._inc(),
                    );
                  },
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}

class CountProvider extends ChangeNotifier {
  int _count = 0;

  int get counterValue => _count;

  void _inc() {
    _count++;
    notifyListeners();
  }

  void _dec() {
    _count--;
    notifyListeners();
  }
}

//////////////////////////////////////////////////////////////
class MyUserPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
          padding: EdgeInsets.all(10.0),
          child: Text('FutureProviderExample, users loaded from a file'),
        ),
        Consumer<List<User>>(
          builder: (BuildContext context, List<User> users, Widget child) {
            return Expanded(
                child: users == null
                    ? Container(
                        child: Center(child: CircularProgressIndicator()),
                      )
                    : ListView.builder(
                        itemCount: users.length,
                        itemBuilder: (context, index) {
                          return Container(
                            height: 50,
                            color: Colors.grey[(index * 200) % 400],
                            child: Center(
                                child: Text(
                                    '${users[index].firstName} ${users[index].lastName} ${users[index].website}')),
                          );
                        }));
          },
        )
      ],
    );
  }
}

class UserProvider {
  final String _dataPath = "assets/users.json";
  List<User> users;

  Future<String> loadAsset() async {
    return await Future.delayed(Duration(seconds: 2), () async {
      return await rootBundle.loadString(_dataPath);
    });
  }

  Future<List<User>> loadUserData() async {
    var dataString = await loadAsset();
    Map<String, dynamic> jsonUserData = jsonDecode(dataString);
    users = UserList.fromJson(jsonUserData['users']).users;
    return users;
  }
}

class User {
  final String firstName, lastName, website;

  User(this.firstName, this.lastName, this.website);

  User.fromJson(Map<String, dynamic> json)
      : this.firstName = json['first_name'],
        this.lastName = json['last_name'],
        this.website = json['website'];
}

class UserList {
  final List<User> users;

  UserList(this.users);

  UserList.fromJson(List<dynamic> usersJson)
      : users = usersJson.map((user) => User.fromJson(user)).toList();
}

//////////////////////////////////////////////////////////////
class MyEventPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var _value = Provider.of<int>(context);
    return Container(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('StreamProvider Example'),
            SizedBox(height: 50,),
            Text('${_value}')
          ],
        ),
      ),
    );
  }
}

class EventProvider {
  Stream<int> intStream() {
    Duration interval = Duration(seconds: 2);
    return Stream<int>.periodic(interval, (int _count) => _count++);
  }
}
/*
  В этом файле используется автоматическая сериализация JSON
  (есть еще ручная - см. http.dart)
  Запуск генерации десериализаторов:
  $ \\work\\flutter\\bin\\flutter.bat packages pub run build_runner build

  Или юзай https://javiercbk.github.io/json_to_dart/
 */

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:json_annotation/json_annotation.dart';

// Генерируемый файл
part 'http_gson_autogenerated.g.dart';

const url = 'https://about.google/static/data/locations.json';

class AutoJsonHttpExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: HttpExample());
  }
}

class HttpExample extends StatefulWidget {
  @override
  _HttpExampleState createState() => _HttpExampleState();
}

class _HttpExampleState extends State<HttpExample> {
  Future<OfficesList> _officesList;

  @override
  void initState() {
    //loadData();
    _officesList = getOfficesList();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.network_wifi),
        title: Text(
          "Network request example",
        ),
        centerTitle: true,
      ),
      body: FutureBuilder<OfficesList>(
        future: _officesList,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return ListView.builder(
                itemCount: snapshot.data.offices.length,
                itemBuilder: (context, index) {
                  return Card(
                    child: ListTile(
                      title: Text('${snapshot.data.offices[index].name}'),
                      subtitle: Text('${snapshot.data.offices[index].address}'),
                      leading:
                          Image.network(snapshot.data.offices[index].image),
                      // 3 строки
                      isThreeLine: true,
                    ),
                  );
                });
          } else if (snapshot.hasError) {
            return Text('Error');
          }
          // Пока происходит запрос - показываем спиннер
          return Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}

Future<http.Response> getData() async {
  return await http.get(url);
}

// Это простой пример
void loadData() {
  getData().then((response) {
    if (response.statusCode == 200) {
      print(response.body);
    } else {
      print(response.statusCode);
    }
  }).catchError((error) {
    debugPrint(error.toString());
  });
}

Future<OfficesList> getOfficesList() async {
  final response = await http.get(url);
  if (response.statusCode == 200) {
    return OfficesList.fromJson(json.decode(response.body));
  } else {
    throw Exception('Error: ${response.reasonPhrase}');
  }
}

@JsonSerializable()
class OfficesList {
  List<Office> offices;

  OfficesList({this.offices});

  factory OfficesList.fromJson(Map<String, dynamic> json) => _$OfficesListFromJson(json);

  Map<String, dynamic> toJson() => _$OfficesListToJson(this);
}

@JsonSerializable()
class Office {
  // @JsonKey() если имя другое
  final String name;
  final String address;
  final String image;

  Office({this.name, this.address, this.image});

  // Фабричный конструктор
  factory Office.fromJson(Map<String, dynamic> json) => _$OfficeFromJson(json);
  Map<String, dynamic> toJson() => _$OfficeToJson(this);
}

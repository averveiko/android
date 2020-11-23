import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:google_fonts/google_fonts.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        // задать шрифт всей теме
        theme: ThemeData(fontFamily: 'NerkoOne'),
        title: 'Flutter Demo',
        home: Scaffold(
          appBar: AppBar(title: Text('Udemy example')),
          //body: Example(),
          //body: StackWidget(),
          //body: IndexedStackWidget(),
          //body: FontWidget(),
          body: ImageWidget(),
        ));
  }
}

class ImageWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final String assetNameJpg = 'assets/images/bali.jpg';
    final String assetNameSvg = 'assets/weather.svg';
    final String assetNameUrl =
        'https://cdn3.iconfinder.com/data/icons/spring-2-1/30/Rainbow-128.png';

    AssetImage imageFromassetImage = AssetImage(assetNameJpg);
    Image imageFromAsset = Image.asset(assetNameJpg, fit: BoxFit.cover);

    Image imageFromNetwork = Image.network(assetNameUrl);

    Image changedImage = Image(
      image: imageFromNetwork.image,
      color: Colors.green,
      colorBlendMode: BlendMode.color,
    );

    SvgPicture svgPicture = SvgPicture.asset(assetNameSvg, fit: BoxFit.none,);

    return Container(
      constraints: BoxConstraints.expand(height: double.infinity),
      /*child: Image(
        image: imageFromassetImage,
        fit: BoxFit.cover,
      ),*/
      //child: imageFromAsset,
      //child: imageFromNetwork,
      //child: changedImage,
      child: svgPicture,
    );
  }
}

class FontWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            'NerkoOne font',
            style: TextStyle(
              fontFamily: 'NerkoOne',
              fontWeight: FontWeight.w100,
              fontSize: 30,
            ),
          ),
          Text(
            'Goldman font',
            style: TextStyle(
              fontFamily: 'Goldman',
              fontWeight: FontWeight.w500,
              fontSize: 30,
            ),
          ),
          Text(
            'BigShouldersStencilDisplay font',
            style: TextStyle(
              fontFamily: 'BigShouldersStencilDisplay',
              fontWeight: FontWeight.w500,
              fontSize: 30,
            ),
          ),
          // Этот не из ресурсов а из пакета google_fonts (смотри pubspec.yaml)
          Text('Google font',
              style: GoogleFonts.lato(
                  textStyle:
                      TextStyle(fontStyle: FontStyle.italic, fontSize: 30))),
        ],
      ),
    );
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

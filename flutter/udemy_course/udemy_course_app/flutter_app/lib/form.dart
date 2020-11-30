import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FormExampleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              leading: Icon(Icons.format_shapes),
              title: Text(
                "Form Example",
              ),
              centerTitle: true,
            ),
            body: RegisterForm()));
  }
}

class RegisterForm extends StatefulWidget {
  @override
  _RegisterFormState createState() => _RegisterFormState();
}

class _RegisterFormState extends State<RegisterForm> {

  bool _hidePass = true;

  @override
  Widget build(BuildContext context) {
    return Form(
      child: ListView(
        padding: EdgeInsets.all(16),
        children: [
          TextField(
            decoration: InputDecoration(
              labelText: 'Full name *',
              hintText: 'What do people call you',
              prefixIcon: Icon(Icons.person),
              suffixIcon: Icon(
                Icons.delete_outline,
                color: Colors.red,
              ),
              enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.black, width: 2)),
              focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.blue, width: 2)),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            keyboardType: TextInputType.phone,
            inputFormatters: [
              FilteringTextInputFormatter.digitsOnly,
            ],
            decoration: InputDecoration(
              labelText: 'Phone number *',
              hintText: 'Where we can reach you',
              helperText: 'Phone format: (XXX)XXX-XXXX',
              prefixIcon: Icon(Icons.call),
              suffixIcon: Icon(
                Icons.delete_outline,
                color: Colors.red,
              ),
              enabledBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.black, width: 2)),
              focusedBorder: OutlineInputBorder(
                  borderRadius: BorderRadius.all(Radius.circular(20)),
                  borderSide: BorderSide(color: Colors.blue, width: 2)),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            keyboardType: TextInputType.emailAddress,
            decoration: InputDecoration(
                labelText: 'Email address',
                hintText: 'Enter a email address',
                icon: Icon(Icons.mail)),
          ),
          SizedBox(
            height: 20,
          ),
          TextFormField(
            decoration: InputDecoration(
              labelText: 'Life story',
              hintText: 'Tell us about your self',
              helperText: 'Keep it short, this is just a demo',
              border: OutlineInputBorder(),
            ),
            maxLines: 3,
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            obscureText: _hidePass,
            maxLength: 8,
            decoration: InputDecoration(
                labelText: 'Password *',
              hintText: 'Enter the password',
              icon: Icon(Icons.security),
              suffixIcon: IconButton(
                icon: Icon(_hidePass ? Icons.visibility : Icons.visibility_off),
                onPressed: (){
                  setState(() {
                    _hidePass = !_hidePass;
                  });
                },
              ),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          TextFormField(
            obscureText: _hidePass,
            maxLength: 8,
            decoration: InputDecoration(
                labelText: 'Confirm password *',
              hintText: 'Confirm the password',
              icon: Icon(Icons.border_color),
            ),
          ),
          SizedBox(
            height: 15,
          ),
          RaisedButton(
            onPressed: () {},
            color: Colors.green,
            child: Text(
              'Submit form',
              style: TextStyle(color: Colors.white),
            ),
          )
        ],
      ),
    );
  }
}

from flask import Flask,request,jsonify
import os

app = Flask(__name__)


def convert_to_celsius(value, from_unit):
    match from_unit:
        case "fahrenheit":
            return value * 9/5 - 32
        
        case "kevin":
            return value - 273.15
        
def convert_to_kevin(value, from_unit):
    match from_unit:
        case "celsius":
            return value + 273.15
        
        case "fahrenheit":
            return (value - 32) * 5/9 +273.15
        
def convert_to_fahrenheit(value, from_unit):
    match from_unit:
        case "celsius":
            return value * 9/5 + 32
        
        case "kevin":
            return (value - 273.15) * 9/5 + 32
        

@app.route("/convert",methods = ["GET"])

def convert():
    unit_from = request.args.get("unit_from")
    unit_to = request.args.get("unit_to")
    value = float(request.args.get("value"))

    match unit_to:
        case "celsius":
            result = convert_to_celsius(value,unit_from)
        case "kevin":
            result = convert_to_kevin(value,unit_from)
        case "fahrenheit":
            result = convert_to_fahrenheit(value,unit_from)
    
    return jsonify({"outcome" : result})

if __name__ == "__main__":
    app.run(debug=True)
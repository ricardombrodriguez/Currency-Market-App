
import json
from flask import request
from flask import Flask
import random

app = Flask(__name__)
dic = dict()

@app.route('/operation', methods=['POST'])
def operation():

    operations = ["BUY","SELL"]
    
    dic = request.get_json(force=True)
    market = dic['market']['symbol']

    operation = random.choice(operations)
    quantity = random.randrange(1,100)

    return json.dumps({'market':market,'operation':operation,'quantity':quantity})

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8000, debug=True)
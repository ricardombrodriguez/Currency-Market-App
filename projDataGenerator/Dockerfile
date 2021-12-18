FROM python:latest

WORKDIR /usr/app/src

COPY . .

RUN pip3 install -r requirements.txt

ADD main.py /

CMD ["python3","main.py"]
from flask import Flask
from config import Config
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_login import LoginManager

app = Flask(__name__) # <-- Create Flask app instance. __name__ is the name of the current module
app.config.from_object(Config) # <-- Load configuration from Config class
db = SQLAlchemy(app) # <-- SQLAlchemy instance. app is the Flask app instance
migrate = Migrate(app, db) # <-- SQLAlchemy and Migrate instances
login = LoginManager(app) # <-- LoginManager instance. app is the Flask app instance
login.login_view = 'login' # <-- Endpoint name for login view

from app import routes, models

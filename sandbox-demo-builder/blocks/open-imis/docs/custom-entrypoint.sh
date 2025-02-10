#!/bin/bash
set -e

show_help() {
  echo """
  Commands
  ---------------------------------------------------------------

  start            : start django
  worker           : start Celery worker

  manage           : run django manage.py
  eval             : eval shell command
  bash             : run bash
  """
}

#export PYTHONPATH="/opt/app:$PYTHONPATH"

  cd ../openIMIS
  echo pwd
  echo "Migrating..."
  python manage.py migrate

  echo "Starting Django..."
  python manage.py runserver 0.0.0.0:8000
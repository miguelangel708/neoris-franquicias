provider "aws" {
  region = var.aws_region
}

resource "aws_db_instance" "franquicias_db" {
  allocated_storage    = 20
  db_name             = var.db_name
  engine              = "mysql"
  engine_version      = "8.0"
  instance_class      = "db.t3.micro"  # Gratis en AWS Free Tier
  username           = var.db_username
  password           = var.db_password
  parameter_group_name = "default.mysql8.0"
  publicly_accessible = true  # Para acceder desde tu máquina local
  skip_final_snapshot = true
}

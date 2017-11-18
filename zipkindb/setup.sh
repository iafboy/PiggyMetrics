#!/bin/bash
set -e

#查看mysql服务的状态，方便调试，这条语句可以删除
echo `service mysql status`

echo '1.启动mysql....'
#启动mysql
service mysql start
sleep 5
echo `service mysql status`

echo '2.begin to load data....'
#导入数据
mysql < /mysql/schema.sql
echo '3.data imported....'

sleep 3
echo `service mysql status`

#重新设置mysql密码
echo '4.begin to change password....'
mysql < /mysql/privileges.sql
echo '5.password changed....'

#sleep 3
echo `service mysql status`
echo `mysql database started`

tail -f /dev/null

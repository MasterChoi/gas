import spidev, time
import threading
import socket
spi = spidev.SpiDev()
spi.open(0,0)

class Sender(threading.Thread):
	def run(self):
		global data
		data = "2"
		while 1:
			c_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
			try:
				c_socket.connect(('52.196.153.48', 9000))
			except Exception as e:
				print(e)
			try:
				c_socket.sendall(data.encode('utf-8'))
			except Exception as e:
				print("fail send")
			c_socket.close()
			time.sleep(1)

class SensorRead(threading.Thread):
		def run(self):
			def analog_read(channel):
				r = spi.xfer2([1, (8+channel) << 4, 0])
				adc_out = ((r[1] & 3) << 8) + r[2]
				return adc_out
			
			while True:
				re = analog_read(0)
				print(re)
				if(re >600):
					data = "1"
				else:
					data = "2"
				time.sleep(0.5)
sensorRead = SensorRead()
sender = Sender()
sensorRead.start()
sender.start()

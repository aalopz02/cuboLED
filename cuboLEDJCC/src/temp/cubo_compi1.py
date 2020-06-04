import serial
import time

connected= False

ser=serial.Serial("COM3",9600)
lista_valores=[1,1,1,1,1,1,1,1,   0,0,0,0,0,0,0,0,  0,1,0,1,0,1]
lista_tiempos=[10,10,10,10,10,10,10,10,   50,50,50,50,50,50,50,50,  1,1,1,1,1,1]
#lista_tiempos=[1000,1000,1000,1000,1000,1000,1000,1000,   5000,5000,5000,5000,5000,5000,5000,5000,  10000,10000,10000,10000,10000,10000]
lista_tiempos_aux=lista_tiempos[:]
lista_escritura=[1,1,1,1,1,1,1,1,   0,0,0,0,0,0,0,0,  0,1,0,1,0,1];
time.sleep(2)

while True:
    
    for i in range(0,len(lista_valores)):
        if(lista_valores[i]==0):
            lista_escritura[i]==0
            ser.write(b'0')
        
        if(lista_valores[i]==1):
            ser.write(b'1')
      
            lista_escritura[i]==1  
    for x in range(0,len(lista_valores)):
        lista_tiempos_aux[x]=lista_tiempos_aux[x]-1
        if(lista_tiempos_aux[x]==0):
            if(lista_valores[x]==0):
                lista_valores[x]=1
            else:
                lista_valores[x]=0
            lista_tiempos_aux[x]=lista_tiempos[x];
    time.sleep(0.02)
   
  

ser.close

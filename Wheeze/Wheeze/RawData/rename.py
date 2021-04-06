import os

#os.rename(old_file_name, new_file_name)
mylist = os.listdir(".")
i=0
for f in mylist:
    if f.split(".")[1]=="txt":  
        print(f)
        os.rename(f,str(i)+".txt")
        i+=1
        

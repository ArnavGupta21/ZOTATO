import java.util.*;

public class Zotato {
    public static Scanner sc=new Scanner(System.in);
    static class Item{
        String name;
        int price;
        int quantity;
        String category;
        int int_offer;
        int code;
        Item(){
            this.name="NA";
            this.quantity=0;
            this.code=0;
        }
        Item(int code,String name,int price,int quantity,String category,int offer){
            this.code=code;
            this.name=name;
            this.price=price;
            this.quantity=quantity;
            this.category=category;
            this.int_offer=offer;
        }
    }
    static class Recent extends Item{
        Item item=new Item();
        String res_name;
        public float total;
        Recent(String food,int quantity,String res_name){
            this.item.name=food;
            this.item.quantity=quantity;
            this.res_name=res_name;
            this.total=0;
        }
        public void print(){
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("You bought "+this.item.name+" from "+this.res_name);
            System.out.println("Quantity - "+this.item.quantity);
            System.out.println("Order Total - "+this.total);
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    static class Owner extends Item {
        Item[] item=new Item[10000];
        String name;
        String category;
        int index;
        public float reward_points;
        int item_count;
        static int total_items=0;
        int overall_discount;

        Owner(int index,String name,String category){
            this.index=index;
            this.name=name;
            this.category=category;
            this.reward_points=0;
            item_count=0;
            overall_discount=0;
        }
        public String getName(){
            return this.name;
        }
        public float getReward_points(){
            return this.reward_points;
        }
        public String Itemname(int code){
            String n="";
            for(int i=0;i<item_count;i++){
                if(item[i].code==code){
                    n=item[i].name;
                    break;
                }
            }
            return n;
        }
        public int GetPrice(int code){
            int n=0;
            for(int i=0;i<item_count;i++){
                if(item[i].code==code){
                    n=item[i].price;
                    break;
                }
            }
            return n;
        }
        public int GetOffer(int code){
            int n=0;
            for(int i=0;i<item_count;i++){
                if(item[i].code==code){
                    n=item[i].int_offer;
                    break;
                }
            }
            return n;

        }
        public String GetCategory(int code){
            String n="";
            for(int i=0;i<item_count;i++){
                if(item[i].code==code){
                    n=item[i].category;
                    break;
                }
            }
            return n;
        }
        public void AddItem(){
            System.out.println("Enter the food Item details");
            System.out.println("Food Name :");
            String food=sc.next();
            System.out.println("Item Price :");
            int price=sc.nextInt();
            System.out.println("item quantity :");
            int q=sc.nextInt();
            System.out.println("Item category :");
            String cat=sc.next();
            System.out.println("Offer :");
            int offer=sc.nextInt();
            item[item_count]=new Item(total_items+1,food,price,q,cat,offer);
            item_count=item_count+1;
            total_items=total_items+1;
            System.out.println(total_items+" "+food+" "+price+" "+q+" "+offer+" "+cat);
        }

        public void EditItem(){
            System.out.println("Choose Item by Code");
            for (int i=0;i<item_count;i++){
                System.out.println(item[i].code+" "+this.name+" - "+item[i].name+" "+item[i].price+" "+item[i].quantity+" "+item[i].int_offer+"% off "+item[i].category);
            }
            int it_code=sc.nextInt();
            System.out.println("Choose an attribute to edit:");
            System.out.println("    1) Name");
            System.out.println("    2) Price");
            System.out.println("    3) Quantity");
            System.out.println("    4) Category");
            System.out.println("    5) Offer");
            int c=sc.nextInt();
            if(c==1){
                System.out.println("Enter the New Name - ");
                item[it_code-1].name= sc.next();
            }
            else if (c==2){
                System.out.println("Enter the New Price - ");
                item[it_code-1].price=sc.nextInt();
            }
            else if(c==3){
                System.out.println("Enter the New Quantity - ");
                item[it_code-1].quantity=sc.nextInt();
            }
            else if (c==4){
                System.out.println("Enter the New Category - ");
                item[it_code-1].category=sc.next();
            }
            else if (c==5){
                System.out.println("Enter the New Offer - ");
                item[it_code-1].int_offer= sc.nextInt();
            }
            System.out.println(item[it_code-1].code+" "+this.name+" - "+item[it_code-1].name+" "+item[it_code-1].price+" "+item[it_code-1].quantity+" "+item[it_code-1].int_offer+"% off "+item[it_code-1].category);
        }

        public void printItems(){
            for(int i=0;i<item_count;i++){
                System.out.println(item[i].code+" - "+item[i].name+" - "+item[i].price+" - "+item[i].quantity+" - "+item[i].int_offer+"% - "+item[i].category);
            }
        }
    }

    static class Cart extends Item{
        Item[] items=new Item[10000];
        int item_count;
        String res_name;
        int res_discount;
        int deliv_charge;

        Cart(){
            this.item_count=0;
            this.res_name="NA";
            this.res_discount=0;
            this.deliv_charge=0;

        }
        public void addItem(int code,String res_name,String food,int price,int quantity,int offer,String cat,int res_discount,int deliv_charge){
            items[item_count]=new Item();
            this.res_name=res_name;
            items[item_count].code=code;
            items[item_count].name=food;
            items[item_count].price=price;
            items[item_count].quantity=quantity;
            items[item_count].category=cat;
            items[item_count].int_offer=offer;
            item_count=item_count+1;
            this.res_discount=res_discount;
            this.deliv_charge=deliv_charge;

        }
        public void printItems(Recent[] recents,int n,float total){
            for (int i=0;i<item_count;i++){
                System.out.println(items[i].code+" "+this.res_name+" - "+items[i].name+" - "+items[i].price+" - "+items[i].quantity+" - "+items[i].int_offer+"%");
                recents[n].name=items[i].name;
                recents[n].res_name=this.res_name;
                recents[n].quantity=items[i].quantity;
                recents[n].total=total;
            }

        }
        public float getTotal(){
            float itemcost=0;
            for(int i=0;i<item_count;i++){
                float x=(items[i].price*items[i].quantity);
                float y=(x*5)/100;
                x=x-y;
                itemcost=itemcost+x;
            }
            float c=(res_discount*itemcost)/100;
            itemcost=itemcost-c;
            if(res_name.equals("Shah") || res_name.equals("The Chinese")){
                if(itemcost>100){
                    itemcost=itemcost-50;
                }
            }
            if(deliv_charge==0){
                if(itemcost>200){
                    itemcost=itemcost-50;
                }
            }
            else if (deliv_charge==20){
                if(itemcost>200){
                    itemcost=itemcost-25;
                }
            }
            itemcost=itemcost+deliv_charge;
            d_charge=d_charge+deliv_charge;
            return itemcost;
        }
    }
    static class Customer extends Cart{
        Cart c;
        Recent[] recents=new Recent[10];
        String name;
        String category;
        int index;
        float rewards;
        float wallet;
        int deliv_charge;
        int total_items;
        String rest;
        int order_counter;
        Customer(String name,String category,int index,int deliv_charge){
            this.name=name;
            this.category=category;
            this.index=index;
            this.deliv_charge=deliv_charge;
            this.rewards=0;
            c=new Cart();
            this.wallet=1000;
            this.total_items=0;
            rest="NA";
            order_counter=0;
            for(int i=0;i<10;i++){
                recents[i]=new Recent("",0,"");
            }
        }

        public String getName(){
            return this.name;
        }
        public float getRewards(){
            return this.rewards;
        }
        public void addToCart(int item_code,String res_name,String food,int price,int offer,String category,int res_discount,int deliv_charge){
            System.out.println("Enter item quantity - " );
            int q=sc.nextInt();
            this.rest=res_name;
            total_items=total_items+q;
            c.addItem(item_code,res_name,food,price,q,offer,category,res_discount,deliv_charge);
            System.out.println("Items added to cart");
        }
        public void printRecent(){
            for(int i=0;i<order_counter;i++){
                recents[i].print();
            }
        }
        public float checkOut(){
            float total=c.getTotal();
            float temp=total;
            if((this.wallet+this.rewards)<total){
                System.out.println("Insufficient Balance in Wallet!");
                System.out.println("Order cannot be placed");
            }

            else {
                System.out.println("Items in Cart - ");
                c.printItems(recents,order_counter,temp);
                System.out.println("Delivery charge - " + this.deliv_charge+"/-");
                System.out.println("Total Order value - INR "+total+"/-");
                System.out.println("    1) Proceed to checkout");
                int con=sc.nextInt();
                if(con==1){
                    System.out.println(total_items+" items successfully bought for INR "+total+"/-");
                    float tmp=total/100;
                    c_balance=c_balance+tmp;
                }
                else {
                    System.out.println("Order Cancelled");
                }
                if(rewards<total){
                    total=total-rewards;
                    rewards=0;
                    wallet=wallet-total;
                }
            }
            order_counter++;
            return temp;
        }
    }
    public static float c_balance=0;
    public static int d_charge=0;

    public static void fill(Owner[] restaurant_list,Customer[] customer_list){
        restaurant_list[0]=new Owner(1,"Shah","Authentic");
        restaurant_list[1]=new Owner(2,"Ravi's","Rest");
        restaurant_list[2]=new Owner(3,"The Chinese","Authentic");
        restaurant_list[3]=new Owner(4,"Wang's","Fast Food");
        restaurant_list[4]=new Owner(5,"Paradise","Rest");
        customer_list[0]=new Customer("Ram","Elite",1,0);
        customer_list[1]=new Customer("Sam","Elite",2,0);
        customer_list[2]=new Customer("Tim","Special",3,20);
        customer_list[3]=new Customer("Kim","Rest",4,40);
        customer_list[4]=new Customer("Jim","Rest",5,40);
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Owner[] restaurant_list=new Owner[5];
        Customer[] customer_list=new Customer[5];
        fill(restaurant_list,customer_list);

        int choice;
        while(true){
            System.out.println("Welcome to Zotato:");
            System.out.println("    1) Enter as Restaurant Owner");
            System.out.println("    2) Enter as Customer");
            System.out.println("    3) Check User Details");
            System.out.println("    4) Company Account Details");
            System.out.println("    5) Exit");
            choice=in.nextInt();
            if(choice==1){
                System.out.println("Choose Restaurant");
                System.out.println("    1) Shah (Authentic)");
                System.out.println("    2) Ravi's");
                System.out.println("    3) The Chinese (Authentic)");
                System.out.println("    4) Wang's (Fast Food)");
                System.out.println("    5) Paradise ");
                int r_choice=in.nextInt();
                while(true){
                    System.out.println("Welcome "+restaurant_list[r_choice-1].getName());
                    System.out.println(" 1) Add Item");
                    System.out.println(" 2) Edit Item");
                    System.out.println(" 3) Print Rewards");
                    System.out.println(" 4) Discount on bill value");
                    System.out.println(" 5) Exit");
                    int menu_choice=in.nextInt();
                    if(menu_choice==1){
                        restaurant_list[r_choice-1].AddItem();
                    }
                    else if (menu_choice==2){
                        restaurant_list[r_choice-1].EditItem();
                    }
                    else if(menu_choice==3){
                        System.out.println("Reward Points :"+restaurant_list[r_choice-1].getReward_points());
                    }
                    else if (menu_choice==4){
                        if(restaurant_list[r_choice - 1].category.equals("Rest")){
                            System.out.println("Offer on Bill Value not Applicable");
                        }
                        else {
                            System.out.println("Offer on Bill value - ");
                            restaurant_list[r_choice-1].overall_discount= in.nextInt();
                        }
                    }
                    else if (menu_choice==5){
                        break;
                    }
                }
            }
            else if(choice==2){
                System.out.println("1. Ram (Elite)");
                System.out.println("2. Sam (Elite)");
                System.out.println("3. Tim (Special)");
                System.out.println("4. Kim");
                System.out.println("5. Jim");
                int c_choice=in.nextInt();
                while(true){
                    System.out.println(" Welcome "+customer_list[c_choice-1].getName());
                    System.out.println(" Customer Menu");
                    System.out.println(" 1) Select restaurant");
                    System.out.println(" 2) Checkout Cart");
                    System.out.println(" 3) Reward Won");
                    System.out.println(" 4) Print the recent orders");
                    System.out.println(" 5) Exit");
                    int menu_choice=in.nextInt();

                    if(menu_choice==1){
                        System.out.println("Choose Restaurant ");
                        System.out.println("    1) Shah (Authentic)");
                        System.out.println("    2) Ravi's");
                        System.out.println("    3) The Chinese (Authentic)");
                        System.out.println("    4) Wang's (Fast Food)");
                        System.out.println("    5) Paradise ");
                        int r_choice=in.nextInt();
                        System.out.println("Choose Item by Code");
                        restaurant_list[r_choice-1].printItems();
                        int item_code=in.nextInt();
                        customer_list[c_choice-1].addToCart(item_code,restaurant_list[r_choice-1].name,restaurant_list[r_choice-1].Itemname(item_code),restaurant_list[r_choice-1].GetPrice(item_code),restaurant_list[r_choice-1].GetOffer(item_code),restaurant_list[r_choice-1].GetCategory(item_code),restaurant_list[r_choice-1].overall_discount,customer_list[c_choice-1].deliv_charge);

                    }
                    else if (menu_choice==2){
                        float total=customer_list[c_choice-1].checkOut();
                        String rest=customer_list[c_choice-1].rest;
                        int reward;
                        if(rest.equals("Shah")){
                            reward=(25*(int)(total/200));
                            customer_list[c_choice-1].rewards+=reward;
                            restaurant_list[0].reward_points+=reward;
                        }
                        else if(rest.equals("Ravi's")){
                            reward=(5*(int)(total/100));
                            customer_list[c_choice-1].rewards+=reward;
                            restaurant_list[1].reward_points+=reward;
                        }
                        else if(rest.equals("The Chinese")){
                            reward=(25*(int)(total/200));
                            customer_list[c_choice-1].rewards+=reward;
                            restaurant_list[2].reward_points+=reward;
                        }
                        else if(rest.equals("Wang's")){
                            reward=(15*(int)(total/150));
                            customer_list[c_choice-1].rewards+=reward;
                            restaurant_list[3].reward_points+=reward;
                        }

                        else if(rest.equals("Paradise")){
                            reward=(5*(int)(total/100));
                            customer_list[c_choice-1].rewards+=reward;
                            restaurant_list[4].reward_points+=reward;
                        }
                    }
                    else if (menu_choice==3){
                        System.out.println("Total Rewards - "+customer_list[c_choice-1].getRewards());
                    }
                    else if (menu_choice==4){
                        customer_list[c_choice-1].printRecent();
                    }
                    else if (menu_choice==5){
                        break;
                    }
                }
            }
            else if(choice==3){
                System.out.println("1) Customer List");
                System.out.println("2) Restaurant List");
                int cud=in.nextInt();
                if(cud==1){
                    System.out.println("1) Ram");
                    System.out.println("2) Sam");
                    System.out.println("3) Tim");
                    System.out.println("4) Kim");
                    System.out.println("5) Jim");
                    int user=in.nextInt();
                    System.out.println(customer_list[user-1].name+"("+customer_list[user-1].category+")"+" , Pune, "+customer_list[user-1].wallet+"/- ");
                }else if(cud==2){
                    System.out.println("1) Shah (Authentic)");
                    System.out.println("2) Ravi's ");
                    System.out.println("3) The Chinese (Authentic)");
                    System.out.println("4) Wang's (Fast Food)");
                    System.out.println("5) Paradise");
                    int res=in.nextInt();
                    System.out.println(restaurant_list[res-1].name+"("+restaurant_list[res-1].category+") , Pune, "+restaurant_list[res-1].getReward_points());
                }
            }
            else if(choice==4){
                System.out.println("Total Company Balance - INR "+c_balance+"/-");
                System.out.println("Total Delivery Charges Collected - INR "+d_charge+"/-");
            }
            else if(choice==5){
                break;
            }
        }
    }
}

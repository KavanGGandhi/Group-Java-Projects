public class ISBNValidator implements IISBNValidator{
    @Override
    public boolean validate(String isbn13) {
        int sum=0 ;
        for (int i=0;i<=12;i++){
            int digit = (int) isbn13.charAt(i)-48;
            if (i%2==0){
                sum+=digit%10;
            } else {
                sum+=3*(digit%10);
            }
            digit=digit/10;
        }
        return sum%10==0;
    }
}

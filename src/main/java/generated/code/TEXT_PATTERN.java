package generated.code;

public enum TEXT_PATTERN {

}
// private static final Pattern END_SEN = Pattern.compile("^(.+)[.]$");  $1
// private static final Pattern INITIALS = Pattern.compile("^([A-Z][.])+$");  $0 with dots or whole string
// private static final Pattern DOTS_IN = Pattern.compile("^([^.]+[.])[^.]+$");
// private static final Pattern FLOAT = Pattern.compile("^([0-9]*[.])[0-9]+$"); $0 with dots or whole string
// private static final Pattern COMMA_NUMBER = Pattern.compile("^([0-9]{1,3}[,])+([0-9]{3}[,])*[0-9]{3}$");
// private static final Pattern ALL_NUMERIC = Pattern.compile("^[0-9]+$");
// private static final Pattern ALL_ALPHA = Pattern.compile("^[a-zA-Z]+$");
// private static final Pattern ALL_CAP = Pattern.compile("^[A-Z]+$");
// private static final Pattern FIRST_CAP = Pattern.compile("^[A-Z][a-z]+$");
// private static final Pattern IRISH_NAME = Pattern.compile("^[A-Z][']?[A-Z][a-z]+$");
// private static final Pattern AL_NUM = Pattern.compile("^(([0-9]+[A-Za-z]+)|([A-Za-z]+[0-9]+))+$");
// private static final Pattern ALL_SYMB = Pattern.compile("^[^A-Za-z0-9]+$");
// private static final Pattern HAS_SYMB = Pattern.compile("[^A-Za-z0-9]");
// private static final Pattern HAS_NUMERIC = Pattern.compile("[0-9]");
// private static final Pattern SINGLE_SYMBOL = Pattern.compile("^[^A-Za-z0-9]$");
// private static final Pattern ALGEBRA_VAR = Pattern.compile("^[A-Za-z]$");
// private static final Pattern TECHNICAL_ALGEBRA = Pattern.compile("^[A-Za-z][0-9]{1,2}$");
// private static final Pattern ELLIPSIS = Pattern.compile("^(.*)[.]{3}$");    "$1"
// private static final Pattern QUESTION = Pattern.compile("^([^?]+)[?]$");    "$1"
// private static final Pattern EXCLAMATION = Pattern.compile("^([^!]+)[!]$"); "$1"
// private static final Pattern SEMICOLON = Pattern.compile("^([^;]+)[;]$");   "$1"
// private static final Pattern COLON = Pattern.compile("^([^:]+)[:]$");       "$1"
// private static final Pattern E_PAR = Pattern.compile("^[(]([^()]+)[)]$");           $1 all
// private static final Pattern E_CURLY = Pattern.compile("^[{]([^{}]+)[}]$");
// private static final Pattern E_CHEVRON = Pattern.compile("^[<]([^<>]+)[>]$");
// private static final Pattern E_BRACK = Pattern.compile("^[\\[]([^\\[\\]]+)[\\]]$");
// private static final Pattern O_BRACK = Pattern.compile("^[\\[]([^\\[\\]]+)$");
// private static final Pattern C_BRACK = Pattern.compile("^([^\\[\\]]+)[\\]]$");
// private static final Pattern OPAR = Pattern.compile("^[(]([^()]+)$");
// private static final Pattern CPAR = Pattern.compile("^([^()]+)[)]$");
// private static final Pattern OCURLY = Pattern.compile("^[{]([^{}]+)$");
// private static final Pattern CCURLY = Pattern.compile("^([^{}]+)[}]$");
// private static final Pattern OCHEVRON = Pattern.compile("^[<]([^<>]+)$");
// private static final Pattern CCHEVRON = Pattern.compile("^([^<>]+)[>]$");
// private static final Pattern ESQ = Pattern.compile("^[']([^']+)[']$");
// private static final Pattern OSQ = Pattern.compile("^[']([^']+)$");
// private static final Pattern CSQ = Pattern.compile("^([^']+)[']$");
// private static final Pattern EDQ = Pattern.compile("^[\"]([^\"]+)[\"]$");
// private static final Pattern ODQ = Pattern.compile("^[\"]([^\"]+)$");
// private static final Pattern CDQ = Pattern.compile("^([^\"]+)[\"]$");
// private static final Pattern POSS1 = Pattern.compile("^([A-Za-z]+)['][sS]$");
// private static final Pattern POSS2 = Pattern.compile("^([A-Za-z]+[sSzZ])[']$");
// private static final Pattern MONEY = Pattern.compile("^[$](([0-9]*[.][0-9]{2})|([0-9]+))$");
// private static final Pattern PERCENT = Pattern.compile("^[%](([0-9]*[.])?[0-9]+)$");
// private static final Pattern NUM_SIGN = Pattern.compile("^[#]([0-9]+)$");
// private static final Pattern SLASH_WORD = Pattern.compile("^([A-Za-z]+)[/]([A-Za-z]+)$"); // $1 $2
// private static final Pattern HYPHEN_WORD = Pattern.compile("^([A-Za-z]+)[-]([A-Za-z]+)$"); // $1 $2
// private static final Pattern NUM_DIVISION = Pattern.compile("^(([0-9]*[.])?[0-9]+)[/](([0-9]*[.])?[0-9]+)$"); // $1 $3
// private static final Pattern NUM_SUBTRACT = Pattern.compile("^(([0-9]*[.])?[0-9]+)[-](([0-9]*[.])?[0-9]+)$"); // $1 $3
// private static final Pattern NUM_SYMB_SEP = Pattern.compile("^(([0-9]*[.])?[0-9]+)([^0-9A-Za-z.])(([0-9]*[.])?[0-9]+)$"); // number:$1 symbol:$3 number:$4
// private static final Pattern AL_NUM_SYMB_SEP = Pattern.compile("^([A-Za-z0-9_.]+)([^A-Za-z0-9_.]{1,2})([A-Za-z0-9_.]+)$"); // alNum:$1 symbol:$2 alNum:$3
// private static final Pattern ENCL_AL_NUM_SYMB_SEP = Pattern.compile("^[({]([^{()}]+)[})]([^A-Za-z0-9_.]{1,2})[({]([^{()}]+)[})]$"); // encl:$1 symbol:$2 encl:$3
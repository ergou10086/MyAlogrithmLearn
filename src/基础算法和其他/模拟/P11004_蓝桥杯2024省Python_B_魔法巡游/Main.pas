program MagicTour;
uses
    SysUtils;

function CheckNumber(num: Integer): Boolean;
var
    n, digit: Integer;
begin
    if num = 0 then
    begin
        CheckNumber := True;
        Exit;
    end;

    n := num;
    while n > 0 do
    begin
        digit := n mod 10;
        if (digit = 0) or (digit = 2) or (digit = 4) then
        begin
            CheckNumber := True;
            Exit;
        end;
        n := n div 10;
    end;
    CheckNumber := False;
end;

var
    n, i, inp, count: Integer;
    si, ti: array of Integer;
begin
    ReadLn(n);
    SetLength(si, n+1);
    SetLength(ti, n+1);

    for i := 1 to n do
        Read(si[i]);
    ReadLn;

    for i := 1 to n do
        Read(ti[i]);
    ReadLn;

    inp := 1;
    count := 0;

    for i := 1 to n do
    begin
        if inp mod 2 = 1 then
        begin
            if CheckNumber(si[i]) then
            begin
                Inc(count);
                Inc(inp);
            end;
        end
        else
        begin
            if CheckNumber(ti[i]) then
            begin
                Inc(count);
                Inc(inp);
            end;
        end;
    end;

    WriteLn(count);
end.
